package com.design.template.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.design.template.ui.theme.Dimens
import com.design.template.ui.theme.customColors

enum class ActionType {
    GPT, COPY, PASTE, SHARE, DELETE
}

@Composable
fun ActionButton(
    type: ActionType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    // Микро-анимация нажатия
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "button_scale"
    )

    val isDanger = type == ActionType.DELETE
    val isGpt = type == ActionType.GPT

    val icon = when (type) {
        ActionType.GPT -> Icons.Outlined.AutoAwesome
        ActionType.COPY -> Icons.Outlined.ContentCopy
        ActionType.PASTE -> Icons.Outlined.ContentPaste
        ActionType.SHARE -> Icons.Outlined.Share
        ActionType.DELETE -> Icons.Outlined.Delete
    }

    // GPT — выделенная кнопка с улучшенным контрастом
    if (isGpt) {
        FilledTonalButton(
            onClick = onClick,
            modifier = modifier
                .size(Dimens.actionButtonSize)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                contentColor = MaterialTheme.colorScheme.primary
            ),
            contentPadding = PaddingValues(0.dp),
            interactionSource = interactionSource,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = type.name,
                modifier = Modifier.size(Dimens.actionIconSize)
            )
        }
        return
    }

    // DELETE — четкая граница с error цветом
    if (isDanger) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier
                .size(Dimens.actionButtonSize)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error
            ),
            border = BorderStroke(
                Dimens.borderWidth,
                MaterialTheme.colorScheme.error.copy(alpha = 0.35f)
            ),
            contentPadding = PaddingValues(0.dp),
            interactionSource = interactionSource
        ) {
            Icon(
                imageVector = icon,
                contentDescription = type.name,
                modifier = Modifier.size(Dimens.actionIconSize)
            )
        }
        return
    }

    // Остальные кнопки — с улучшенными границами
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .size(Dimens.actionButtonSize)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        ),
        border = BorderStroke(
            Dimens.borderWidth,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        ),
        contentPadding = PaddingValues(0.dp),
        interactionSource = interactionSource
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
