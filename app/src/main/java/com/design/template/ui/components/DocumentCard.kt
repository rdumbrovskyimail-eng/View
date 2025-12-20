package com.design.template.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
        border = BorderStroke(Dimens.borderWidth, MaterialTheme.colorScheme.outline)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Отображение изображения
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
                    // Placeholder
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "📄",
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        )
                    }
                }
            }
            
            // Loading overlay
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
    Column(modifier = modifier.fillMaxWidth()) {
        // Image + Scanned Text
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
        
        // Divider
        HorizontalDivider(
            modifier = Modifier.padding(vertical = Dimens.dividerMargin),
            thickness = Dimens.dividerHeight,
            color = MaterialTheme.customColors.divider
        )
        
        // Translated Text
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
