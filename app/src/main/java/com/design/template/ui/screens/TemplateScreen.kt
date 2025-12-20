package com.design.template.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

// Добавляем DocumentItem в SampleItem.kt или создаем здесь временно
private data class DocumentItem(
    val id: Int,
    val title: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentViewerScreen() {
    val scrollState = rememberScrollState()
    var isFavorite by remember { mutableStateOf(false) }
    
    val relatedDocuments = remember {
        listOf(
            DocumentItem(1, "Video 1", "https://picsum.photos/seed/vid1/300/400"),
            DocumentItem(2, "Photo 2", "https://picsum.photos/seed/vid2/300/400"),
            DocumentItem(3, "Person", "https://picsum.photos/seed/person/300/400"),
            DocumentItem(4, "Code 1", "https://picsum.photos/seed/code1/300/400"),
            DocumentItem(5, "Code 2", "https://picsum.photos/seed/code2/300/400"),
            DocumentItem(6, "Gallery", "https://picsum.photos/seed/gallery/300/400")
        )
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.RemoveRedEye,
                            contentDescription = "View",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "More",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            BottomActionsBar()
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // Main Content Area
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                
                // Document Card
                DocumentCard(
                    isFavorite = isFavorite,
                    onFavoriteClick = { isFavorite = !isFavorite }
                )
                
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            // Bottom Carousel
            DocumentCarousel(documents = relatedDocuments)
        }
    }
}

@Composable
private fun DocumentCard(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Project Contract - A4",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "B",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = "Final version for client review, November 25.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Content Row - Preview + Description
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Document Preview Image
                Card(
                    modifier = Modifier
                        .width(140.dp)
                        .height(180.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    AsyncImage(
                        model = "https://picsum.photos/seed/document/400/600",
                        contentDescription = "Document preview",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                
                // Description Text
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(180.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = "AGREEMENT BETWEEN PARTIES...",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text(
                                text = "The document between the Client 'sweetslatintely, beneath the LIMITED HONTON COMPLETED PARTIES', and ComposeDions. The document both and Marker and said.\n\n" +
                                        "PARTIES, this users the client's remeralities of no-imbarked advices and its personal likely relate to the end applicate under's but is theucciment.\n\n" +
                                        "PARTIES, areement and minusent",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                                lineHeight = MaterialTheme.typography.bodySmall.lineHeight
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ActionButton(
                    icon = Icons.Default.SmartToy,
                    text = "GPT",
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    icon = Icons.Default.ContentCopy,
                    text = "Copy",
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    icon = Icons.Default.Photo,
                    text = "Photo",
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    icon = Icons.Default.Send,
                    text = "Send",
                    modifier = Modifier.weight(1f),
                    isPrimary = true
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Footer Russian Text
            Text(
                text = "ДОГОВОР МЕЖДУ СТОРОНАМИ, ИСТИЕР? ІТОНИНТ TO ВНИИН КПАСНИ ГНЕВНИЯ БЕДЬ FOR THE МАРКО ЕЦ ЕВЕ ОІ. СРЕДАТИ И ДОГОВОР У ENERGY OF THE COMMODS FOR IYARS OF PLIMILITY OF USE-FOR LING AND DECENATION YН А СЕПІН МЕТАЛ, І ДОГОВОР ХІ МЕЖДУ СТОРОНАМИ ПОСІЛОДЮТТІ УПРОС МІСИТ, СОНІНЬ ДИЯЕСЇ ДПОСОВНИНИЙ ЦЕПОСТОСТИ ПОДЕЇТУ БЫІТРАТЛЯ.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun ActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    isPrimary: Boolean = false
) {
    OutlinedButton(
        onClick = { },
        modifier = modifier.height(40.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isPrimary) 
                MaterialTheme.colorScheme.primaryContainer 
            else 
                Color.Transparent,
            contentColor = if (isPrimary)
                MaterialTheme.colorScheme.onPrimaryContainer
            else
                MaterialTheme.colorScheme.onSurface
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            width = 1.dp,
            brush = androidx.compose.ui.graphics.SolidColor(
                if (isPrimary)
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                else
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            )
        ),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun BottomActionsBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomActionItem(icon = Icons.Default.Share, label = "Share")
            BottomActionItem(icon = Icons.Default.Edit, label = "Edit")
            BottomActionItem(icon = Icons.Default.RecordVoiceOver, label = "Speak")
            BottomActionItem(icon = Icons.Default.LocalOffer, label = "Tags")
            BottomActionItem(icon = Icons.Default.MoreHoriz, label = "More")
            BottomActionItem(
                icon = Icons.Default.Delete,
                label = "Delete",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
private fun BottomActionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    tint: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = tint,
                modifier = Modifier.size(22.dp)
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = tint,
            maxLines = 1
        )
    }
}

@Composable
private fun DocumentCarousel(documents: List<DocumentItem>) {
    Column {
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
        
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.surface),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(documents, key = { it.id }) { doc ->
                Card(
                    modifier = Modifier
                        .width(80.dp)
                        .height(96.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    AsyncImage(
                        model = doc.imageUrl,
                        contentDescription = doc.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

// Обновляем TemplateScreen для добавления новой вкладки
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
            // Tabs
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }
            
            // Content
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
