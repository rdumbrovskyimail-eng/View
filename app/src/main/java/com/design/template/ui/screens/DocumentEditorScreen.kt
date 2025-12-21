package com.design.template.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.design.template.ui.components.AddDocumentFAB
import com.design.template.ui.components.DocumentBlock
import com.design.template.ui.theme.Dimens
import com.design.template.ui.theme.customColors

data class DocumentItem(
    val id: String,
    val imageBitmap: ImageBitmap? = null,
    val imageUrl: String? = null,
    val scannedText: String,
    val translatedText: String,
    val isLoading: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentEditorScreen(
    folderName: String,
    documentTitle: String,
    documentDescription: String,
    documents: List<DocumentItem>,
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onGptClick: (documentId: String) -> Unit,
    onCopyClick: (documentId: String) -> Unit,
    onPasteClick: (documentId: String) -> Unit,
    onShareClick: (documentId: String) -> Unit,
    onDeleteClick: (documentId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val customColors = MaterialTheme.customColors

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background,
                shadowElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.topBarHeight)
                        .padding(horizontal = Dimens.topBarPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(Dimens.topBarIconSize),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = folderName,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = "Menu",
                            modifier = Modifier.size(Dimens.topBarIconSize),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            AddDocumentFAB(
                onCameraClick = onCameraClick,
                onGalleryClick = onGalleryClick,
                modifier = Modifier.padding(
                    end = Dimens.fabEndPadding,
                    bottom = Dimens.fabBottomPadding
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = Dimens.spaceSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceMedium)
        ) {

            // ===== DOCUMENT HEADER =====
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = Dimens.spaceLarge,
                            bottom = Dimens.spaceLarge
                        )
                ) {
                    Text(
                        text = documentTitle,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = documentDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = customColors.textSecondary
                    )
                }
            }

            // ===== DOCUMENT BLOCKS =====
            items(documents) { doc ->
                DocumentBlock(
                    imageBitmap = doc.imageBitmap,
                    imageUrl = doc.imageUrl,
                    isLoading = doc.isLoading,
                    scannedText = doc.scannedText,
                    translatedText = doc.translatedText,
                    onGptClick = { onGptClick(doc.id) },
                    onCopyClick = { onCopyClick(doc.id) },
                    onPasteClick = { onPasteClick(doc.id) },
                    onShareClick = { onShareClick(doc.id) },
                    onDeleteClick = { onDeleteClick(doc.id) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(96.dp))
            }
        }
    }
}
