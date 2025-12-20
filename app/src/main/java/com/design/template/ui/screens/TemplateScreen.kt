package com.design.template.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage

@Composable
fun TemplateScreen() {
    DocumentViewerScreen()
}

@Composable
fun DocumentViewerScreen() {
    val scrollState = rememberScrollState()
    var isTranslating by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding() // Отступ от status bar (время, батарея)
            .verticalScroll(scrollState)
    ) {
        // Top Bar - Название папки со стрелкой
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "НАЗВАНИЕ ПАПКИ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
        )
        
        // Название документа
        Text(
            text = "НАЗВАНИЕ ДОКУМЕНТА",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 14.dp),
            fontSize = 20.sp,
            letterSpacing = 0.15.sp
        )
        
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f)
        )
        
        // Описание документа - МЕНЬШИЙ шрифт
        Text(
            text = "ОПИСАНИЕ ДОКУМЕНТА",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            fontSize = 12.sp,
            letterSpacing = 0.25.sp
        )
        
        Spacer(modifier = Modifier.height(1.dp))
        
        // Main Content Row - Image (50%) + Text (50%)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            // Left - Image with loading animation
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .aspectRatio(1f / 1.414f)
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                SubcomposeAsyncImage(
                    model = "https://picsum.photos/seed/document/1000/1414",
                    contentDescription = "Document image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    loading = {
                        LoadingImagePlaceholder()
                    }
                )
                
                // Тонкая правая граница
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .align(Alignment.CenterEnd)
                        .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
                )
            }
            
            // Right - Original Text (90%) + Buttons (10%)
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .aspectRatio(1f / 1.414f)
            ) {
                // Original Text - 90%
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.9f)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "ОРИГИНАЛЬНЫЙ ТЕКСТ",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 9.sp,
                            letterSpacing = 0.5.sp
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "The document between the Client 'sweetslatintely, beneath the LIMITED HONTON COMPLETED PARTIES', and ComposeDions. The document both and Marker and said.\n\nPARTIES, this users the client's remeralities...",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.sp
                        )
                    }
                }
                
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                )
                
                // Buttons Row - 10%
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f)
                        .background(MaterialTheme.colorScheme.surface),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MiniActionButton(icon = Icons.Outlined.AutoAwesome, contentDesc = "GPT")
                    MiniActionButton(icon = Icons.Outlined.ContentCopy, contentDesc = "Copy")
                    MiniActionButton(icon = Icons.Outlined.ContentPaste, contentDesc = "Paste")
                    MiniActionButton(icon = Icons.Outlined.Share, contentDesc = "Share")
                }
            }
        }
        
        // Тонкая линия разделения
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
        )
        
        Spacer(modifier = Modifier.height(1.dp))
        
        // TRANSLATE TEXT Section
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp)
        ) {
            val imageHeight = maxWidth * 0.5f * 1.414f
            val translateHeight = imageHeight * 0.25f
            
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = translateHeight),
                color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.5f)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Header с иконкой и названием
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
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "TRANSLATE TEXT",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 13.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                    
                    // Translated text
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        if (isTranslating) {
                            TranslatingAnimation()
                        } else {
                            Text(
                                text = "Документ между Клиентом 'sweetslatintely, под LIMITED HONTON COMPLETED PARTIES', и ComposeDions. Документ как и Marker и сказал.\n\nСТОРОНЫ, это пользователи клиента remeralities of no-imbarked советы и его личное вероятно относятся к концу подать под это theucciment.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                letterSpacing = 0.sp
                            )
                        }
                    }
                    
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                    )
                    
                    // Bottom buttons - ВНИЗУ как ты просил!
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Left - Delete button
                        IconButton(
                            onClick = { },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                Icons.Outlined.DeleteOutline,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        
                        // Right - Action buttons
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MiniActionButton(icon = Icons.Outlined.AutoAwesome, contentDesc = "GPT")
                            MiniActionButton(icon = Icons.Outlined.ContentCopy, contentDesc = "Copy")
                            MiniActionButton(icon = Icons.Outlined.ContentPaste, contentDesc = "Paste")
                            MiniActionButton(icon = Icons.Outlined.Share, contentDesc = "Share")
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun LoadingImagePlaceholder() {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
                        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Outlined.Image,
            contentDescription = "Loading",
            tint = MaterialTheme.colorScheme.primary.copy(alpha = alpha),
            modifier = Modifier
                .size(48.dp)
                .scale(scale)
        )
    }
}

@Composable
private fun TranslatingAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "translating")
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .alpha(if (index % 2 == 0) alpha else 1f - alpha)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            )
            if (index < 2) Spacer(modifier = Modifier.width(6.dp))
        }
    }
}

@Composable
private fun MiniActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDesc: String
) {
    IconButton(
        onClick = { },
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
