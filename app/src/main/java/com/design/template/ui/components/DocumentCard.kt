package com.design.template.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.InsertDriveFile
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.design.template.ui.theme.Dimens
import com.design.template.ui.theme.customColors

@Composable
fun DocumentImageCard(
    imageBitmap: ImageBitmap?,
    imageUrl: String? = null,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    val customColors = MaterialTheme.customColors
    
    Surface(
        modifier = modifier
            .width(Dimens.imageWidth)
            .height(Dimens.imageHeight),
        color = customColors.fieldBg,
        shape = MaterialTheme.shapes.small,
        tonalElevation = 2.dp,
        shadowElevation = 2.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                imageBitmap != null -> {
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = "Document image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                imageUrl != null -> {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Document image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.InsertDriveFile,
                            contentDescription = "Document placeholder",
                            modifier = Modifier.size(96.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                        )
                    }
                }
            }
            
            if (isLoading) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 3.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScannedTextCard(
    text: String,
    onGptClick: () -> Unit,
    onCopyClick: () -> Unit,
    onPasteClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val customColors = MaterialTheme.customColors
    
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.imageHeight),
        color = customColors.fieldBg,
        shape = MaterialTheme.shapes.small,
        tonalElevation = 2.dp,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(Dimens.textFieldPadding)
        ) {
            Text(
                text = "SCANNED TEXT",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = Dimens.spaceSmall)
            )
            
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            )
            
            Spacer(modifier = Modifier.height(Dimens.spaceSmall))
            
            ActionButtonRow(
                onGptClick = onGptClick,
                onCopyClick = onCopyClick,
                onPasteClick = onPasteClick,
                onShareClick = onShareClick
            )
        }
    }
}

@Composable
fun TranslatedTextCard(
    text: String,
    onDeleteClick: () -> Unit,
    onGptClick: () -> Unit,
    onCopyClick: () -> Unit,
    onPasteClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val customColors = MaterialTheme.customColors
    
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = customColors.fieldBg,
        shape = MaterialTheme.shapes.small,
        tonalElevation = 2.dp,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(Dimens.textFieldPadding)
        ) {
            Text(
                text = "TRANSLATED TEXT",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = Dimens.spaceSmall)
            )
            
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = Dimens.spaceSmall)
            )
            
            ActionButtonRowWithDelete(
                onDeleteClick = onDeleteClick,
                onGptClick = onGptClick,
                onCopyClick = onCopyClick,
                onPasteClick = onPasteClick,
                onShareClick = onShareClick
            )
        }
    }
}

@Composable
fun DocumentBlock(
    imageBitmap: ImageBitmap?,
    imageUrl: String? = null,
    isLoading: Boolean = false,
    scannedText: String,
    translatedText: String,
    onGptClick: () -> Unit,
    onCopyClick: () -> Unit,
    onPasteClick: () -> Unit,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColors.cardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall)
            ) {
                DocumentImageCard(
                    imageBitmap = imageBitmap,
                    imageUrl = imageUrl,
                    isLoading = isLoading
                )
                
                ScannedTextCard(
                    text = scannedText,
                    onGptClick = onGptClick,
                    onCopyClick = onCopyClick,
                    onPasteClick = onPasteClick,
                    onShareClick = onShareClick,
                    modifier = Modifier.weight(1f)
                )
            }
            
            HorizontalDivider(
                modifier = Modifier.padding(vertical = Dimens.dividerMargin),
                thickness = Dimens.dividerHeight,
                color = MaterialTheme.customColors.divider
            )
            
            TranslatedTextCard(
                text = translatedText,
                onDeleteClick = onDeleteClick,
                onGptClick = onGptClick,
                onCopyClick = onCopyClick,
                onPasteClick = onPasteClick,
                onShareClick = onShareClick
            )
        }
    }
}
