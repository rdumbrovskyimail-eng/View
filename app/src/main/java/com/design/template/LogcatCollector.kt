import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class LogcatCollector(private val context: Context) {
    
    private var logcatProcess: Process? = null
    private var collectJob: Job? = null
    private val logBuffer = StringBuilder()
    private val maxBufferSize = 5 * 1024 * 1024 // 5MB лимит буфера
    
    companion object {
        @Volatile
        private var instance: LogcatCollector? = null
        
        fun getInstance(context: Context): LogcatCollector {
            return instance ?: synchronized(this) {
                instance ?: LogcatCollector(context.applicationContext).also { 
                    instance = it 
                }
            }
        }
    }
    
    fun startCollecting() {
        if (collectJob?.isActive == true) return
        
        android.util.Log.d("LogcatCollector", "Starting logcat collection...")
        
        collectJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                // Очищаем старые логи
                Runtime.getRuntime().exec("logcat -c").waitFor()
                delay(50)
                
                // Получаем PID приложения
                val pid = android.os.Process.myPid()
                
                // Запускаем logcat с расширенными параметрами
                logcatProcess = Runtime.getRuntime().exec(
                    arrayOf(
                        "logcat",
                        "-v", "threadtime",
                        "--pid=$pid",
                        "-b", "main,system,crash"
                    )
                )
                
                val reader = BufferedReader(
                    InputStreamReader(logcatProcess!!.inputStream),
                    16384
                )
                
                var line: String?
                while (isActive && reader.readLine().also { line = it } != null) {
                    line?.let { logLine ->
                        synchronized(logBuffer) {
                            logBuffer.append(logLine).append("\n")
                            
                            // Ограничиваем размер буфера
                            if (logBuffer.length > maxBufferSize) {
                                logBuffer.delete(0, logBuffer.length - maxBufferSize)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                android.util.Log.e("LogcatCollector", "Error collecting logs", e)
                synchronized(logBuffer) {
                    logBuffer.append("\n=== ERROR IN COLLECTOR ===\n")
                    logBuffer.append(e.stackTraceToString())
                    logBuffer.append("\n")
                }
            }
        }
        
        // Устанавливаем обработчик для крашей
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            try {
                android.util.Log.e("LogcatCollector", "App crashed!", throwable)
                
                synchronized(logBuffer) {
                    logBuffer.append("\n\n")
                    logBuffer.append("=".repeat(60)).append("\n")
                    logBuffer.append("FATAL EXCEPTION in thread: ${thread.name}\n")
                    logBuffer.append("=".repeat(60)).append("\n")
                    logBuffer.append(throwable.stackTraceToString())
                    logBuffer.append("\n")
                }
                
                // Немедленно сохраняем при краше
                saveLogsToFileBlocking()
            } catch (e: Exception) {
                android.util.Log.e("LogcatCollector", "Failed to save crash log", e)
            } finally {
                // Передаем управление стандартному обработчику
                defaultHandler?.uncaughtException(thread, throwable)
            }
        }
    }
    
    fun stopCollecting() {
        try {
            collectJob?.cancel()
            logcatProcess?.destroy()
            saveLogsToFileBlocking()
        } catch (e: Exception) {
            android.util.Log.e("LogcatCollector", "Error stopping collector", e)
        }
    }
    
    private fun saveLogsToFileBlocking() {
        try {
            val timestamp = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault())
                .format(Date())
            val fileName = "logcat_$timestamp.txt"
            
            val logContent = synchronized(logBuffer) {
                buildString {
                    append("=".repeat(60)).append("\n")
                    append("Logcat dump: $timestamp\n")
                    append("Package: ${context.packageName}\n")
                    append("Android Version: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})\n")
                    append("Device: ${Build.MANUFACTURER} ${Build.MODEL}\n")
                    append("=".repeat(60)).append("\n\n")
                    append(logBuffer.toString())
                }
            }
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Android 10+ (включая Android 16): используем MediaStore
                val resolver = context.contentResolver
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                }
                
                val uri = resolver.insert(
                    MediaStore.Downloads.EXTERNAL_CONTENT_URI, 
                    contentValues
                )
                
                uri?.let {
                    resolver.openOutputStream(it)?.use { outputStream ->
                        outputStream.write(logContent.toByteArray())
                    }
                    android.util.Log.i("LogcatCollector", "Logs saved: $fileName")
                }
            } else {
                // Android 9 и ниже (на всякий случай)
                val downloadsDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
                )
                val logFile = File(downloadsDir, fileName)
                FileOutputStream(logFile).use { fos ->
                    fos.write(logContent.toByteArray())
                }
                android.util.Log.i("LogcatCollector", "Logs saved: ${logFile.absolutePath}")
            }
        } catch (e: Exception) {
            android.util.Log.e("LogcatCollector", "Failed to save logs", e)
        }
    }
    
    fun forceSave() {
        saveLogsToFileBlocking()
    }
}
