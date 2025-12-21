package com.design.template.ui.components

import androidx.compose.foundation.BorderStroke
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
import com.design.template.ui.theme.Dimens
import com.design.template.ui.theme.customColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDocumentFAB(
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    // ===== FAB — тихий, не доминирующий =====
    FloatingActionButton(
        onClick = { showBottomSheet = true },
        modifier = modifier.size(Dimens.fabSize),
        shape = MaterialTheme.shapes.small,
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Add document",
            modifier = Modifier.size(22.dp)
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.large,
            tonalElevation = 0.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.bottomSheetPadding)
            ) {

                // ===== Handle — очень тихий =====
                Box(
                    modifier = Modifier
                        .width(Dimens.bottomSheetHandleWidth)
                        .height(Dimens.bottomSheetHandleHeight)
                        .align(Alignment.CenterHorizontally)
                ) {
                    HorizontalDivider(
                        thickness = Dimens.bottomSheetHandleHeight,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.spaceMedium)
                ) {

                    // ===== Camera =====
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
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CameraAlt,
                                contentDescription = "Camera",
                                modifier = Modifier.size(Dimens.bottomSheetIconSize),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Camera",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                            )
                        }
                    }

                    // ===== Gallery =====
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
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PhotoLibrary,
                                contentDescription = "Gallery",
                                modifier = Modifier.size(Dimens.bottomSheetIconSize),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Gallery",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                            )
                        }
                    }
                }
            }
        }
    }
}
