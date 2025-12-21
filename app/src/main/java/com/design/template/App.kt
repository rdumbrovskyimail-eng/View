package com.design.template

import android.app.Application

class App : Application() {
    
    private lateinit var logcatCollector: LogcatCollector
    
    override fun onCreate() {
        super.onCreate()
        
        // ВАЖНО: Запускаем коллектор логов ПЕРВЫМ ДЕЛОМ
        logcatCollector = LogcatCollector.getInstance(this)
        logcatCollector.startCollecting()
        
        android.util.Log.d("App", "Application started with LogcatCollector")
        
        // Хук для сохранения при завершении
        Runtime.getRuntime().addShutdownHook(Thread {
            logcatCollector.forceSave()
        })
    }
    
    override fun onTerminate() {
        super.onTerminate()
        logcatCollector.stopCollecting()
    }
}
