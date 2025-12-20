package com.design.template.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage

/* ENTRY */

@Composable
fun TemplateScreen() {
    DocumentViewerScreen()
}

/* SCREEN */

@Composable
fun DocumentViewerScreen() {
    val scrollState = rememberScrollState()
    var isTranslating by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {

        TopFolderBar()
        DocumentHeader()
        DocumentDescription()
        MainDocumentContent()
        TranslateSection(isTranslating)

        Spacer(modifier = Modifier.height(16.dp))
    }
}

/* TOP BAR */

@Composable
private fun TopFolderBar() {
    Surface(tonalElevation = 1.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "НАЗВАНИЕ ПАПКИ",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
    SoftDivider()
}

/* HEADER */

@Composable
private fun DocumentHeader() {
    Surface {
        Text(
            text = "НАЗВАНИЕ ДОКУМЕНТА",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
    SoftDivider(alpha = 0.2f)
}

/* DESCRIPTION */

@Composable
private fun DocumentDescription() {
    Surface {
        Text(
            text = "ОПИСАНИЕ ДОКУМЕНТА",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/* MAIN CONTENT */

@Composable
private fun MainDocumentContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp)
    ) {

        // IMAGE
        Surface(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(1f / 1.414f),
            tonalElevation = 1.dp
        ) {
            SubcomposeAsyncImage(
                model = "https://picsum.photos/seed/document/1000/1414",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = { LoadingImagePlaceholder() }
            )
        }

        // TEXT
        Surface(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(1f / 1.414f),
            tonalElevation = 1.dp
        ) {
            Column {
                OriginalTextBlock(
                    modifier = Modifier.weight(0.9f)
                )
                SoftDivider()
                ActionButtonsRow(
                    modifier = Modifier.weight(0.1f)
                )
            }
        }
    }
    SoftDivider()
}

/* ORIGINAL TEXT */

@Composable
private fun OriginalTextBlock(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "ORIGINAL TEXT",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "The document between the Client 'sweetslatintely, beneath the LIMITED HONTON COMPLETED PARTIES', and ComposeDions...",
            style = MaterialTheme.typography.bodySmall,
            lineHeight = 16.sp
        )
    }
}

/* ACTION BUTTONS */

@Composable
private fun ActionButtonsRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MiniActionButton(Icons.Outlined.AutoAwesome)
        MiniActionButton(Icons.Outlined.ContentCopy)
        MiniActionButton(Icons.Outlined.ContentPaste)
        MiniActionButton(Icons.Outlined.Share)
    }
}

/* TRANSLATE SECTION */

@Composable
private fun TranslateSection(isTranslating: Boolean) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp)
    ) {
        val imageHeight = maxWidth * 0.5f * 1.414f

        Surface(
            modifier = Modifier.heightIn(min = imageHeight * 0.25f),
            tonalElevation = 1.dp
        ) {
            Column {

                TranslateHeader()

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (isTranslating) TranslatingAnimation()
                    else Text(
                        text = "Переведённый текст документа...",
                        style = MaterialTheme.typography.bodySmall,
                        lineHeight = 16.sp
                    )
                }

                SoftDivider()

                TranslateBottomBar()
            }
        }
    }
}

/* TRANSLATE HEADER */

@Composable
private fun TranslateHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Outlined.Translate,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "TRANSLATE TEXT",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/* TRANSLATE BOTTOM */

@Composable
private fun TranslateBottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = {}, modifier = Modifier.size(32.dp)) {
            Icon(
                Icons.Outlined.DeleteOutline,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            MiniActionButton(Icons.Outlined.AutoAwesome)
            MiniActionButton(Icons.Outlined.ContentCopy)
            MiniActionButton(Icons.Outlined.ContentPaste)
            MiniActionButton(Icons.Outlined.Share)
        }
    }
}

/* COMPONENTS */

@Composable
private fun MiniActionButton(icon: androidx.compose.ui.graphics.vector.ImageVector) {
    IconButton(onClick = {}, modifier = Modifier.size(32.dp)) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun SoftDivider(alpha: Float = 0.3f) {
    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = alpha)
    )
}

/* LOADING IMAGE */

@Composable
private fun LoadingImagePlaceholder() {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")

    val alpha by infiniteTransition.animateFloat(
        0.3f, 0.7f,
        infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "alpha"
    )

    val scale by infiniteTransition.animateFloat(
        0.95f, 1.05f,
        infiniteRepeatable(tween(1500), RepeatMode.Reverse),
        label = "scale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
                        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Outlined.Image,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .scale(scale)
                .alpha(alpha),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

/* TRANSLATING ANIMATION */

@Composable
private fun TranslatingAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")

    val alpha by infiniteTransition.animateFloat(
        0.4f, 1f,
        infiniteRepeatable(tween(800), RepeatMode.Reverse),
        label = "alpha"
    )

    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .alpha(alpha)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        CircleShape
                    )
            )
        }
    }
}
