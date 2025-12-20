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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
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

/* SCREEN (адаптировано под Grok: минимализм, элегантные отступы, мягкие тени, плавные анимации) */

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
            .padding(bottom = 16.dp),  // Для комфорта внизу
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopFolderBar()
        DocumentHeader()
        DocumentDescription()
        MainDocumentContent(isTranslating = isTranslating, onTranslateClick = { isTranslating = !isTranslating })
        if (isTranslating) TranslateSection()

        Spacer(modifier = Modifier.height(24.dp))  // Элегантный отступ для "кайфа"
    }
}

/* TOP BAR (минималистичный, как в Grok) */

@Composable
private fun TopFolderBar() {
    Surface(
        tonalElevation = 2.dp,  // Мягкая тень для глубины
        modifier = Modifier.shadow(4.dp, MaterialTheme.shapes.small)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = "НАЗВАНИЕ ПАПКИ",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

/* HEADER (элегантный, центрированный) */

@Composable
private fun DocumentHeader() {
    Surface(color = Color.Transparent) {
        Text(
            text = "НАЗВАНИЕ ДОКУМЕНТА",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    SoftDivider(alpha = 0.1f)  // Очень мягкий divider
}

/* DESCRIPTION (с большим lineHeight для читаемости) */

@Composable
private fun DocumentDescription() {
    Surface(color = Color.Transparent) {
        Text(
            text = "ОПИСАНИЕ ДОКУМЕНТА. Это может быть подробное описание, чтобы пользователь мог легко понять контекст.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/* MAIN CONTENT (Row с изображением и текстом, эстетично: с клипом и тенью) */

@Composable
private fun MainDocumentContent(isTranslating: Boolean, onTranslateClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(8.dp),  // Внутренний отступ для элегантности
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // IMAGE (фото книги, с crop и placeholder анимацией)
        Surface(
            modifier = Modifier
                .weight(0.45f)
                .aspectRatio(0.7f)  // Портретный формат для книги
                .clip(MaterialTheme.shapes.medium)
                .shadow(2.dp),
            tonalElevation = 2.dp
        ) {
            SubcomposeAsyncImage(
                model = "https://picsum.photos/seed/book/800/1200",  // Фото книги (случайное, но тематическое)
                contentDescription = "Изображение документа (книга)",
                contentScale = ContentScale.Crop,
                loading = { LoadingImagePlaceholder() }
            )
        }

        // TEXT (оригинал с кнопками, элегантно организовано)
        Surface(
            modifier = Modifier
                .weight(0.55f)
                .aspectRatio(0.7f)
                .clip(MaterialTheme.shapes.medium)
                .shadow(2.dp),
            tonalElevation = 2.dp
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                OriginalTextBlock(
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp)
                )
                SoftDivider(alpha = 0.2f)
                ActionButtonsRow(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(horizontal = 8.dp),
                    isTranslating = isTranslating,
                    onTranslateClick = onTranslateClick
                )
            }
        }
    }
    SoftDivider(alpha = 0.1f)
}

/* ORIGINAL TEXT (с scroll и лучшей типографикой) */

@Composable
private fun OriginalTextBlock(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "ОРИГИНАЛЬНЫЙ ТЕКСТ",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "The document between the Client 'sweetslatintely, beneath the LIMITED HONTON COMPLETED PARTIES', and ComposeDions... (полный текст документа на английском или другом языке).",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/* ACTION BUTTONS (добавлена кнопка Translate для toggling, иконки в стиле Grok - простые, spaced) */

@Composable
private fun ActionButtonsRow(modifier: Modifier = Modifier, isTranslating: Boolean, onTranslateClick: () -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MiniActionButton(Icons.Outlined.Translate, onClick = onTranslateClick)  // Toggle translate
        MiniActionButton(Icons.Outlined.ContentCopy)
        MiniActionButton(Icons.Outlined.ContentPaste)
        MiniActionButton(Icons.Outlined.Share)
    }
}

/* TRANSLATE SECTION (анимация translating, элегантный box с градиентом) */

@Composable
private fun TranslateSection() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clip(MaterialTheme.shapes.large)
            .shadow(4.dp),
        tonalElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TranslateHeader()
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)  // Фиксированная высота для эстетики
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.surfaceVariant,
                                MaterialTheme.colorScheme.background
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                TranslatingAnimation()  // Анимация во время перевода
                Text(
                    text = "Переведённый текст документа... (здесь будет полный перевод на русский или выбранный язык).",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.alpha(0.8f)  // Лёгкая прозрачность для стиля
                )
            }
            Spacer(Modifier.height(8.dp))
            TranslateBottomBar()
        }
    }
}

/* TRANSLATE HEADER (иконка + текст, aligned) */

@Composable
private fun TranslateHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
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
            text = "ПЕРЕВОД ТЕКСТА",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Medium
        )
    }
}

/* TRANSLATE BOTTOM (кнопки с delete и actions, spaced evenly) */

@Composable
private fun TranslateBottomBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
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

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            MiniActionButton(Icons.Outlined.AutoAwesome)
            MiniActionButton(Icons.Outlined.ContentCopy)
            MiniActionButton(Icons.Outlined.ContentPaste)
            MiniActionButton(Icons.Outlined.Share)
        }
    }
}

/* COMPONENTS (улучшены для эстетики: большие иконки, анимации) */

@Composable
private fun MiniActionButton(icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit = {}) {
    IconButton(onClick = onClick, modifier = Modifier.size(40.dp)) {  // Чуть больше для "кайфа"
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(22.dp),
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

/* LOADING IMAGE (анимация с градиентом и scale для smoothness) */

@Composable
private fun LoadingImagePlaceholder() {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")

    val alpha by infiniteTransition.animateFloat(
        0.4f, 0.8f,
        infiniteRepeatable(tween(1200), RepeatMode.Reverse),
        label = "alpha"
    )

    val scale by infiniteTransition.animateFloat(
        0.98f, 1.02f,
        infiniteRepeatable(tween(1800), RepeatMode.Reverse),
        label = "scale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f),
                        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.15f)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Outlined.Book,  // Иконка книги для тематики
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .scale(scale)
                .alpha(alpha),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

/* TRANSLATING ANIMATION (плавные точки, как в Grok "thinking") */

@Composable
private fun TranslatingAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")

    val alpha by infiniteTransition.animateFloat(
        0.5f, 1f,
        infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "alpha"
    )

    Row(
        modifier = Modifier.padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .alpha(alpha)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        CircleShape
                    )
            )
        }
    }
}
