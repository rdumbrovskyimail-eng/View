package com.design.template.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.design.template.ui.theme.Dimens
import com.design.template.ui.theme.customColors

@Composable
fun ScannedTextCard(
    text: String,
    onGptClick: () -> Unit,
    onCopyClick: () -> Unit,
    onPasteClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val customColors = MaterialTheme.customColors
    
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.imageHeight),
        color = customColors.fieldBg,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(Dimens.borderWidth, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier.padding(Dimens.textFieldPadding)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            )
            
            Spacer(modifier = Modifier.height(Dimens.spaceSmall))
            
            ActionButtonRow(
                onGptClick = onGptClick,
                onCopyClick = onCopyClick,
                onPasteClick = onPasteClick,
                onShareClick = onShareClick
            )
        }
    }
}

@Composable
fun TranslatedTextCard(
    text: String,
    onDeleteClick: () -> Unit,
    onGptClick: () -> Unit,
    onCopyClick: () -> Unit,
    onPasteClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val customColors = MaterialTheme.customColors
    
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = customColors.fieldBg,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(Dimens.borderWidth, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier.padding(Dimens.textFieldPadding)
        ) {
            Text(
                text = "TRANSLATED TEXT",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = Dimens.spaceSmall)
            )
            
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = Dimens.spaceSmall)
            )
            
            ActionButtonRowWithDelete(
                onDeleteClick = onDeleteClick,
                onGptClick = onGptClick,
                onCopyClick = onCopyClick,
                onPasteClick = onPasteClick,
                onShareClick = onShareClick
            )
        }
    }
}
