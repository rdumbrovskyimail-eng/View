package com.design.template.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage

// --- Constants ---
private const val A4_ASPECT_RATIO = 1f / 1.414f

@Composable
fun TemplateScreen() {
    // В реальном приложении данные приходят из ViewModel
    DocumentViewerScreen()
}

@Composable
fun DocumentViewerScreen() {
    val scrollState = rememberScrollState()
    
    // Поднимаем состояние (State Hoisting)
    var isTranslating by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        FolderTopBar(folderName = "НАЗВАНИЕ ПАПКИ")
        
        AppDivider()
        
        DocumentHeader(
            title = "НАЗВАНИЕ ДОКУМЕНТА",
            description = "ОПИСАНИЕ ДОКУМЕНТА"
        )
        
        Spacer(modifier = Modifier.height(1.dp))
        
        // Основной контент (Сплит вид)
        SplitDocumentContent(
            imageUrl = "https://picsum.photos/seed/document/1000/1414",
            originalText = "The document between the Client 'sweetslatintely, beneath the LIMITED HONTON COMPLETED PARTIES', and ComposeDions.\n\nPARTIES, this users the client's remeralities..."
        )
        
        AppDivider()
        Spacer(modifier = Modifier.height(1.dp))
        
        // Секция перевода
        TranslationSection(
            isTranslating = isTranslating,
            translatedText = "Документ между Клиентом 'sweetslatintely, под LIMITED HONTON COMPLETED PARTIES', и ComposeDions.\n\nСТОРОНЫ, это пользователи клиента remeralities...",
            onDeleteClick = { /* Logic */ },
            onActionClick = { /* Logic */ }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

// --- Composable Components ---

@Composable
private fun FolderTopBar(folderName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = folderName,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.1.sp
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun DocumentHeader(title: String, description: String) {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.15.sp
            ),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        )
        
        AppDivider(alpha = 0.2f)
        
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall.copy(
                letterSpacing = 0.25.sp
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}

@Composable
private fun SplitDocumentContent(
    imageUrl: String,
    originalText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min), // Важно: выравнивает высоту колонок
        horizontalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        // Left: Image Viewer
        Box(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(A4_ASPECT_RATIO)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = "Document Scan",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                loading = { LoadingImagePlaceholder() },
                error = { 
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(Icons.Outlined.BrokenImage, null)
                    }
                }
            )
            
            // Вертикальный разделитель справа
            VerticalDivider(
                modifier = Modifier.align(Alignment.CenterEnd),
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
            )
        }
        
        // Right: Text & Actions
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight() // Заполняет высоту, заданную картинкой
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // Text Area
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "ОРИГИНАЛЬНЫЙ ТЕКСТ",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = originalText,
                    style = MaterialTheme.typography.bodySmall.copy(
                        lineHeight = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            
            AppDivider()
            
            // Context Actions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp), // Фиксированная высота для кнопок
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionIcon(Icons.Outlined.AutoAwesome, "AI")
                ActionIcon(Icons.Outlined.ContentCopy, "Copy")
                ActionIcon(Icons.Outlined.ContentPaste, "Paste")
                ActionIcon(Icons.Outlined.Share, "Share")
            }
        }
    }
}

@Composable
private fun TranslationSection(
    isTranslating: Boolean,
    translatedText: String,
    onDeleteClick: () -> Unit,
    onActionClick: () -> Unit
) {
    // Используем Surface для лучшего визуального выделения блока
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.5f)
    ) {
        Column {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Translate,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "TRANSLATE TEXT",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            
            // Content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .heightIn(min = 100.dp) // Минимальная высота для эстетики
            ) {
                if (isTranslating) {
                    TranslatingAnimation()
                } else {
                    Text(
                        text = translatedText,
                        style = MaterialTheme.typography.bodySmall.copy(
                            lineHeight = 16.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            
            AppDivider()
            
            // Bottom Toolbar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteOutline,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(20.dp)
                    )
                }
                
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    ActionIcon(Icons.Outlined.AutoAwesome, "AI", onClick = onActionClick)
                    ActionIcon(Icons.Outlined.ContentCopy, "Copy", onClick = onActionClick)
                    ActionIcon(Icons.Outlined.ContentPaste, "Paste", onClick = onActionClick)
                    ActionIcon(Icons.Outlined.Share, "Share", onClick = onActionClick)
                }
            }
        }
    }
}

// --- UI Helpers ---

@Composable
private fun AppDivider(alpha: Float = 0.3f) {
    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = alpha)
    )
}

@Composable
private fun ActionIcon(
    icon: ImageVector,
    contentDesc: String,
    onClick: () -> Unit = {}
) {
    // Используем IconButton для правильного Ripple эффекта
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(32.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDesc,
            modifier = Modifier.size(18.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun LoadingImagePlaceholder() {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f, targetValue = 0.6f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse), label = "alpha"
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = alpha)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Outlined.Image, null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
        )
    }
}

@Composable
private fun TranslatingAnimation() {
    val transition = rememberInfiniteTransition(label = "dots")
    val alpha by transition.animateFloat(
        initialValue = 0.2f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(600), RepeatMode.Reverse), label = "alpha"
    )
    
    Row(
        modifier = Modifier.fillMaxSize(), // Заполняет доступное место
        horizontalArrangement = Arrangement.Center, // Центрируем
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(3) { 
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .alpha(if (it % 2 == 0) alpha else 1f - alpha)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}
