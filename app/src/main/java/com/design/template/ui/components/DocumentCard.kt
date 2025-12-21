package com.design.template.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.InsertDriveFile
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    Surface(
        modifier = modifier
            .width(Dimens.imageWidth)
            .height(Dimens.imageHeight)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f),
                shape = MaterialTheme.shapes.small
            ),
        color = MaterialTheme.customColors.fieldBg,
        shape = MaterialTheme.shapes.small,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                imageBitmap != null -> {
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = "Document image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(MaterialTheme.shapes.small),
                        contentScale = ContentScale.Crop
                    )
                }

                imageUrl != null -> {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Document image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(MaterialTheme.shapes.small),
                        contentScale = ContentScale.Crop
                    )
                }

                else -> {
                    // Placeholder с демо-изображением
                    AsyncImage(
                        model = "https://picsum.photos/400/600",
                        contentDescription = "Document placeholder",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(MaterialTheme.shapes.small),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            if (isLoading) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp),
                            strokeWidth = 3.dp,
                            color = MaterialTheme.colorScheme.primary
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
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.imageHeight),
        color = MaterialTheme.customColors.fieldBg,
        shape = MaterialTheme.shapes.small,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.textFieldPadding)
        ) {
            Text(
                text = "SCANNED TEXT",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 12,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
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
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                shape = MaterialTheme.shapes.small
            ),
        color = MaterialTheme.customColors.fieldBg,
        shape = MaterialTheme.shapes.small,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.textFieldPadding)
        ) {
            Text(
                text = "TRANSLATED TEXT",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
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
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 3.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f),
                shape = MaterialTheme.shapes.large
            ),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColors.cardBg
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
        ) {
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
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
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
