package com.design.template.ui.components

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
    Surface(
        modifier = modifier
            .width(Dimens.imageWidth)
            .height(Dimens.imageHeight),
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
                    Icon(
                        imageVector = Icons.Outlined.InsertDriveFile,
                        contentDescription = "Document placeholder",
                        modifier = Modifier
                            .size(72.dp)
                            .align(Alignment.Center),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.15f)
                    )
                }
            }

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(28.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                    )
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
        Column(modifier = Modifier.padding(Dimens.textFieldPadding)) {

            Text(
                text = "Scanned text",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
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
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.customColors.fieldBg,
        shape = MaterialTheme.shapes.small,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Column(modifier = Modifier.padding(Dimens.textFieldPadding)) {

            Text(
                text = "Translated text",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = Dimens.spaceSmall)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = Dimens.spaceSmall)
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
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColors.cardBg
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
                color = MaterialTheme.customColors.divider.copy(alpha = 0.6f)
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
