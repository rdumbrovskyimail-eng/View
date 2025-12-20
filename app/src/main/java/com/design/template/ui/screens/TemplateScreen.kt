package com.design.template.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

private data class DocumentItem(
    val id: Int,
    val title: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentViewerScreen() {
    val scrollState = rememberScrollState()
    
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            // Top Section - Back Arrow + Headers
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                                MaterialTheme.colorScheme.surface
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                // Back Arrow
                IconButton(
                    onClick = { },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Название папки
                Text(
                    text = "← НАЗВАНИЕ ПАПКИ",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Название документа
                Text(
                    text = "НАЗВАНИЕ ДОКУМЕНТА",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Описание документа
                Text(
                    text = "ОПИСАНИЕ ДОКУМЕНТА",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Main Content Row - 50% Image + 50% Text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Left - Image (50%)
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxHeight()
                ) {
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = "https://picsum.photos/seed/document/600/800",
                                contentDescription = "Document image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            
                            // Optional buttons at bottom
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.Black.copy(alpha = 0.7f)
                                            )
                                        )
                                    )
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                repeat(4) { index ->
                                    Surface(
                                        modifier = Modifier.size(10.dp),
                                        shape = CircleShape,
                                        color = if (index == 0) 
                                            MaterialTheme.colorScheme.primary 
                                        else 
                                            Color.White.copy(alpha = 0.5f)
                                    ) {}
                                    if (index < 3) {
                                        Spacer(modifier = Modifier.width(8.dp))
                                    }
                                }
                            }
                        }
                    }
                }
                
                // Right - Original Text (50%)
                Card(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "ОРИГИНАЛЬНЫЙ ТЕКСТ",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Text(
                            text = "The document between the Client 'sweetslatintely, beneath the LIMITED HONTON COMPLETED PARTIES', and ComposeDions. The document both and Marker and said.\n\n" +
                                    "PARTIES, this users the client's remeralities of no-imbarked advices and its personal likely relate to the end applicate under's but is theucciment.\n\n" +
                                    "PARTIES, areement and minusent agreement between parties...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 22.sp
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // TRANSLATE TEXT Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            Icons.Default.Translate,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = "TRANSLATE TEXT",
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Документ между Клиентом 'sweetslatintely, под LIMITED HONTON COMPLETED PARTIES', и ComposeDions. Документ как и Marker и сказал.\n\n" +
                                "СТОРОНЫ, это пользователи клиента remeralities of no-imbarked советы и его личное вероятно относятся к концу подать под это theucciment.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        lineHeight = 24.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Optional Buttons Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FilledTonalButton(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Share",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
                
                FilledTonalButton(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Edit",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// Keep existing TemplateScreen and other functions
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Cards", "Buttons", "Inputs", "Dialogs", "Document")
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Design Template",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, "Search")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, "More")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {},
                icon = { Icon(Icons.Default.Add, "Add") },
                text = { Text("Add Item") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }
            
            when (selectedTab) {
                0 -> CardsDemo()
                1 -> ButtonsDemo()
                2 -> InputsDemo()
                3 -> DialogsDemo()
                4 -> DocumentViewerScreen()
            }
        }
    }
}

@Composable
private fun CardsDemo() {
    val items = remember {
        List(10) {
            com.design.template.domain.model.SampleItem(
                id = it.toLong(),
                title = "Item ${it + 1}",
                description = "This is a sample description for item ${it + 1}",
                status = if (it % 2 == 0) "Active" else "Pending"
            )
        }
    }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items, key = { it.id }) { item ->
            com.design.template.ui.components.SampleCard(
                item = item,
                onClick = {},
                onLongClick = {}
            )
        }
    }
}

@Composable
private fun ButtonsDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Filled Buttons", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {}) {
                Icon(Icons.Default.Check, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(4.dp))
                Text("Primary")
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Secondary")
            }
        }
        
        HorizontalDivider()
        
        Text("Outlined Buttons", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = {}) {
                Text("Outlined")
            }
            OutlinedButton(onClick = {}, enabled = false) {
                Text("Disabled")
            }
        }
        
        HorizontalDivider()
        
        Text("Text Buttons", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextButton(onClick = {}) {
                Text("Text Button")
            }
            TextButton(
                onClick = {},
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Icon(Icons.Default.Delete, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(4.dp))
                Text("Delete")
            }
        }
        
        HorizontalDivider()
        
        Text("Icon Buttons", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Favorite, "Favorite")
            }
            FilledIconButton(onClick = {}) {
                Icon(Icons.Default.Star, "Star")
            }
            FilledTonalIconButton(onClick = {}) {
                Icon(Icons.Default.Edit, "Edit")
            }
        }
    }
}

@Composable
private fun InputsDemo() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableFloatStateOf(0.5f) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = text1,
            onValueChange = { text1 = it },
            label = { Text("Name") },
            placeholder = { Text("Enter your name") },
            leadingIcon = { Icon(Icons.Default.Person, null) },
            modifier = Modifier.fillMaxWidth()
        )
        
        OutlinedTextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { Text("Email") },
            placeholder = { Text("example@mail.com") },
            leadingIcon = { Icon(Icons.Default.Email, null) },
            modifier = Modifier.fillMaxWidth()
        )
        
        HorizontalDivider()
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Enable notifications")
            Switch(checked = checked, onCheckedChange = { checked = it })
        }
        
        HorizontalDivider()
        
        Text("Volume: ${(sliderValue * 100).toInt()}%")
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun DialogsDemo() {
    var showBasicDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showInputDialog by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = { showBasicDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show Basic Dialog")
        }
        
        Button(
            onClick = { showConfirmDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show Confirm Dialog")
        }
        
        Button(
            onClick = { showInputDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show Input Dialog")
        }
    }
    
    if (showBasicDialog) {
        com.design.template.ui.components.BasicDialog(
            onDismiss = { showBasicDialog = false }
        )
    }
    
    if (showConfirmDialog) {
        com.design.template.ui.components.ConfirmDialog(
            onConfirm = { showConfirmDialog = false },
            onDismiss = { showConfirmDialog = false }
        )
    }
    
    if (showInputDialog) {
        com.design.template.ui.components.InputDialog(
            onConfirm = { showInputDialog = false },
            onDismiss = { showInputDialog = false }
        )
    }
}
