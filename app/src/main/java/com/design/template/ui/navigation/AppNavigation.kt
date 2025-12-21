package com.design.template.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.design.template.ui.screens.DocumentEditorScreen
import com.design.template.ui.screens.DocumentItem

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // временные данные, чтобы приложение СТАРТОВАЛО
    val demoDocuments = listOf(
        DocumentItem(
            id = "1",
            scannedText = "Scanned text example",
            translatedText = "Translated text example"
        )
    )

    NavHost(
        navController = navController,
        startDestination = "editor"
    ) {
        composable("editor") {
            DocumentEditorScreen(
                folderName = "My Documents",
                documentTitle = "Document title",
                documentDescription = "Document description",
                documents = demoDocuments,
                onBackClick = {},
                onMenuClick = {},
                onCameraClick = {},
                onGalleryClick = {},
                onGptClick = {},
                onCopyClick = {},
                onPasteClick = {},
                onShareClick = {},
                onDeleteClick = {}
            )
        }
    }
}
