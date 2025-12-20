package com.design.template.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.design.template.ui.theme.Dimens
import com.design.template.ui.theme.customColors

enum class ActionType {
    GPT,
    COPY,
    PASTE,
    SHARE,
    DELETE
}

@Composable
fun ActionButton(
    type: ActionType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDanger = type == ActionType.DELETE
    val customColors = MaterialTheme.customColors
    
    val icon = when (type) {
        ActionType.GPT -> Icons.Outlined.AutoAwesome
        ActionType.COPY -> Icons.Outlined.ContentCopy
        ActionType.PASTE -> Icons.Outlined.ContentPaste
        ActionType.SHARE -> Icons.Outlined.Share
        ActionType.DELETE -> Icons.Outlined.Delete
    }
    
    val containerColor = if (isDanger) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        customColors.chipBg
    }
    
    val contentColor = if (isDanger) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = if (isDanger) {
        MaterialTheme.colorScheme.error.copy(alpha = 0.3f)
    } else {
        MaterialTheme.colorScheme.outline
    }
    
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.size(Dimens.actionButtonSize),
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(Dimens.borderWidth, borderColor),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = type.name,
            modifier = Modifier.size(Dimens.actionIconSize)
        )
    }
}

@Composable
fun ActionButtonRow(
    onGptClick: () -> Unit,
    onCopyClick: () -> Unit,
    onPasteClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall)
    ) {
        ActionButton(type = ActionType.GPT, onClick = onGptClick)
        ActionButton(type = ActionType.COPY, onClick = onCopyClick)
        ActionButton(type = ActionType.PASTE, onClick = onPasteClick)
        ActionButton(type = ActionType.SHARE, onClick = onShareClick)
    }
}

@Composable
fun ActionButtonRowWithDelete(
    onDeleteClick: () -> Unit,
    onGptClick: () -> Unit,
    onCopyClick: () -> Unit,
    onPasteClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton(type = ActionType.DELETE, onClick = onDeleteClick)
        
        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall)) {
            ActionButton(type = ActionType.GPT, onClick = onGptClick)
            ActionButton(type = ActionType.COPY, onClick = onCopyClick)
            ActionButton(type = ActionType.PASTE, onClick = onPasteClick)
            ActionButton(type = ActionType.SHARE, onClick = onShareClick)
        }
    }
}
