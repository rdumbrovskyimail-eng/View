package com.design.template.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun DocumentViewerScreen() {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
    ) {
        // Top Bar - Тонкая серая линия со стрелкой и названием папки
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                .padding(horizontal = 4.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "← НАЗВАНИЕ ПАПКИ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 13.sp
            )
        }
        
        // Название документа - Жирный, увеличенный текст
        Text(
            text = "НАЗВАНИЕ ДОКУМЕНТА",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            fontSize = 22.sp
        )
        
        // Описание документа - Белая колонка, меньший шрифт
        Text(
            text = "ОПИСАНИЕ ДОКУМЕНТА",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            fontSize = 14.sp
        )
        
        Spacer(modifier = Modifier.height(2.dp))
        
        // Main Content Row - Image (50%) + Text (50%)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            // Left - Image with 1:1.414 ratio (A4 proportion)
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .aspectRatio(1f / 1.414f)
            ) {
                AsyncImage(
                    model = "https://picsum.photos/seed/document/1000/1414",
                    contentDescription = "Document image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            
            // Right - Original Text (90%) + Buttons (10%)
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .aspectRatio(1f / 1.414f)
            ) {
                // Original Text - 90%
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.9f),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "ОРИГИНАЛЬНЫЙ ТЕКСТ",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 10.sp
                        )
                        
                        Spacer(modifier = Modifier.height(4.dp))
                        
                        Text(
                            text = "The document between the Client 'sweetslatintely, beneath the LIMITED HONTON COMPLETED PARTIES', and ComposeDions. The document both and Marker and said.\n\nPARTIES, this users the client's remeralities...",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 11.sp,
                            lineHeight = 14.sp
                        )
                    }
                }
                
                // Buttons Row - 10%
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f)
                        .background(MaterialTheme.colorScheme.surface),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MiniActionButton(icon = Icons.Default.SmartToy, label = "GPT")
                    MiniActionButton(icon = Icons.Default.ContentCopy, label = "Copy")
                    MiniActionButton(icon = Icons.Default.ContentPaste, label = "Paste")
                    MiniActionButton(icon = Icons.Default.Share, label = "Share")
                }
            }
        }
        
        Spacer(modifier = Modifier.height(2.dp))
        
        // TRANSLATE TEXT Section - 20-30% от высоты картинки
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
                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Left - Delete button
                        IconButton(
                            onClick = { },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Translate,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "TRANSLATE TEXT",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = 14.sp
                            )
                        }
                        
                        // Right - Action buttons
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            MiniActionButton(icon = Icons.Default.SmartToy, label = "")
                            MiniActionButton(icon = Icons.Default.ContentCopy, label = "")
                            MiniActionButton(icon = Icons.Default.ContentPaste, label = "")
                            MiniActionButton(icon = Icons.Default.Share, label = "")
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Документ между Клиентом 'sweetslatintely, под LIMITED HONTON COMPLETED PARTIES', и ComposeDions. Документ как и Marker и сказал.\n\nСТОРОНЫ, это пользователи клиента remeralities of no-imbarked советы и его личное вероятно относятся к концу подать под это theucciment.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun MiniActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(2.dp)
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 9.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun TemplateScreen() {
    DocumentViewerScreen()
}
