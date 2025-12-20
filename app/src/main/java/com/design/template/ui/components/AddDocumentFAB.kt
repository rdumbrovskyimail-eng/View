package com.yourapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourapp.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDocumentFAB(
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    
    FloatingActionButton(
        onClick = { showBottomSheet = true },
        modifier = modifier.size(Dimens.fabSize),
        shape = MaterialTheme.shapes.small,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Add document",
            modifier = Modifier.size(24.dp)
        )
    }
    
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.bottomSheetPadding)
            ) {
                // Handle
                Box(
                    modifier = Modifier
                        .width(Dimens.bottomSheetHandleWidth)
                        .height(Dimens.bottomSheetHandleHeight)
                        .align(Alignment.CenterHorizontally)
                ) {
                    HorizontalDivider(
                        thickness = Dimens.bottomSheetHandleHeight,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Camera Button
                    OutlinedButton(
                        onClick = {
                            onCameraClick()
                            showBottomSheet = false
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(72.dp),
                        shape = MaterialTheme.shapes.small,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.customColors.chipBg
                        ),
                        border = BorderStroke(
                            Dimens.borderWidth,
                            MaterialTheme.colorScheme.outline
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CameraAlt,
                                contentDescription = "Camera",
                                modifier = Modifier.size(Dimens.bottomSheetIconSize)
                            )
                            Text(
                                text = "Camera",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                    
                    // Gallery Button
                    OutlinedButton(
                        onClick = {
                            onGalleryClick()
                            showBottomSheet = false
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(72.dp),
                        shape = MaterialTheme.shapes.small,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.customColors.chipBg
                        ),
                        border = BorderStroke(
                            Dimens.borderWidth,
                            MaterialTheme.colorScheme.outline
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PhotoLibrary,
                                contentDescription = "Gallery",
                                modifier = Modifier.size(Dimens.bottomSheetIconSize)
                            )
                            Text(
                                text = "Gallery",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
