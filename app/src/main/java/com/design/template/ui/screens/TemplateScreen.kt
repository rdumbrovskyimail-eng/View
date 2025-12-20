package com.design.template.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.design.template.domain.model.SampleItem
import com.design.template.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Cards", "Buttons", "Inputs", "Dialogs")
    
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
            }
        }
    }
}

@Composable
private fun CardsDemo() {
    val items = remember {
        List(10) {
            SampleItem(
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
            SampleCard(
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
        
        Divider()
        
        Text("Outlined Buttons", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = {}) {
                Text("Outlined")
            }
            OutlinedButton(onClick = {}, enabled = false) {
                Text("Disabled")
            }
        }
        
        Divider()
        
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
        
        Divider()
        
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
        
        Divider()
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Enable notifications")
            Switch(checked = checked, onCheckedChange = { checked = it })
        }
        
        Divider()
        
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
        BasicDialog(
            onDismiss = { showBasicDialog = false }
        )
    }
    
    if (showConfirmDialog) {
        ConfirmDialog(
            onConfirm = { showConfirmDialog = false },
            onDismiss = { showConfirmDialog = false }
        )
    }
    
    if (showInputDialog) {
        InputDialog(
            onConfirm = { showInputDialog = false },
            onDismiss = { showInputDialog = false }
        )
    }
}
