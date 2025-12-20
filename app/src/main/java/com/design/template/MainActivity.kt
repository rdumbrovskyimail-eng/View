package com.yourapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.yourapp.ui.screens.DocumentEditorScreen
import com.yourapp.ui.screens.DocumentItem
import com.yourapp.ui.theme.DocumentScannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocumentScannerApp()
        }
    }
}

@Composable
fun DocumentScannerApp() {
    var isDarkTheme by remember { mutableStateOf(false) }
    
    // Sample data
    var documents by remember {
        mutableStateOf(
            listOf(
                DocumentItem(
                    id = "1",
                    scannedText = """
                        INVOICE #2024-001
                        Date: December 20, 2025
                        Total Amount: $5,420.00
                        
                        Description:
                        Professional services rendered for web development project including frontend implementation, backend API integration, and deployment.
                    """.trimIndent(),
                    translatedText = """
                        СЧЕТ-ФАКТУРА #2024-001
                        Дата: 20 декабря 2025
                        Общая сумма: $5,420.00
                        
                        Описание:
                        Профессиональные услуги по веб-разработке, включая frontend реализацию, интеграцию backend API и развертывание.
                    """.trimIndent(),
                    isLoading = false
                )
            )
        )
    }
    
    DocumentScannerTheme(darkTheme = isDarkTheme) {
        DocumentEditorScreen(
            folderName = "Work Documents",
            documentTitle = "Invoice Report 2024",
            documentDescription = "Financial documentation for Q4 fiscal reporting",
            documents = documents,
            onBackClick = {
                // Handle back navigation
                // navController.navigateUp()
            },
            onMenuClick = {
                // Show menu options (save, close, etc.)
            },
            onCameraClick = {
                // Launch camera
                // cameraLauncher.launch()
            },
            onGalleryClick = {
                // Launch gallery picker
                // galleryLauncher.launch("image/*")
            },
            onGptClick = { documentId ->
                // Send to GPT for processing
                println("GPT clicked for document: $documentId")
            },
            onCopyClick = { documentId ->
                // Copy text to clipboard
                println("Copy clicked for document: $documentId")
            },
            onPasteClick = { documentId ->
                // Paste from clipboard
                println("Paste clicked for document: $documentId")
            },
            onShareClick = { documentId ->
                // Share document
                println("Share clicked for document: $documentId")
            },
            onDeleteClick = { documentId ->
                // Delete document block
                documents = documents.filter { it.id != documentId }
            }
        )
    }
}

// ViewModel пример
/*
class DocumentEditorViewModel : ViewModel() {
    private val _documents = mutableStateListOf<DocumentItem>()
    val documents: List<DocumentItem> = _documents
    
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    
    fun addDocumentFromCamera(imageBitmap: ImageBitmap) {
        val newDoc = DocumentItem(
            id = UUID.randomUUID().toString(),
            imageBitmap = imageBitmap,
            scannedText = "",
            translatedText = "",
            isLoading = true
        )
        _documents.add(newDoc)
        
        // Start OCR processing
        processOCR(newDoc.id, imageBitmap)
    }
    
    fun addDocumentFromGallery(imageUri: Uri) {
        viewModelScope.launch {
            val bitmap = loadBitmapFromUri(imageUri)
            bitmap?.let { addDocumentFromCamera(it) }
        }
    }
    
    private fun processOCR(documentId: String, image: ImageBitmap) {
        viewModelScope.launch {
            try {
                // Call OCR API (Google ML Kit, Tesseract, etc.)
                val scannedText = ocrService.extractText(image)
                
                // Call translation API
                val translatedText = translationService.translate(scannedText)
                
                // Update document
                val index = _documents.indexOfFirst { it.id == documentId }
                if (index != -1) {
                    _documents[index] = _documents[index].copy(
                        scannedText = scannedText,
                        translatedText = translatedText,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                // Handle error
                Log.e("OCR", "Error processing document", e)
            }
        }
    }
    
    fun deleteDocument(documentId: String) {
        _documents.removeAll { it.id == documentId }
    }
    
    fun copyToClipboard(text: String, context: Context) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("document_text", text)
        clipboard.setPrimaryClip(clip)
    }
}
*/
