package com.design.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.design.template.ui.screens.DocumentEditorScreen
import com.design.template.ui.screens.DocumentItem
import com.design.template.ui.theme.DocumentScannerTheme

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
            },
            onMenuClick = {
                // Show menu options
            },
            onCameraClick = {
                // Launch camera
            },
            onGalleryClick = {
                // Launch gallery picker
            },
            onGptClick = { documentId ->
                println("GPT clicked for document: $documentId")
            },
            onCopyClick = { documentId ->
                println("Copy clicked for document: $documentId")
            },
            onPasteClick = { documentId ->
                println("Paste clicked for document: $documentId")
            },
            onShareClick = { documentId ->
                println("Share clicked for document: $documentId")
            },
            onDeleteClick = { documentId ->
                documents = documents.filter { it.id != documentId }
            }
        )
    }
}
