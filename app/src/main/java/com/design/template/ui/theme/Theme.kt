package com.design.template.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = Color.White,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightPrimary,
    
    secondary = LightSecondary,
    onSecondary = Color.White,
    
    surface = LightSurface,
    onSurface = LightText,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightOnSurfaceVariant,
    
    background = LightBackground,
    onBackground = LightText,
    
    outline = LightOutline,
    outlineVariant = LightOutline.copy(alpha = 0.5f),
    
    error = LightError,
    onError = Color.White,
    errorContainer = LightErrorBg,
    onErrorContainer = LightError,
    
    scrim = Color.Black.copy(alpha = 0.32f)
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = Color.White,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkPrimary,
    
    secondary = DarkSecondary,
    onSecondary = Color.White,
    
    surface = DarkSurface,
    onSurface = DarkText,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,
    
    background = DarkBackground,
    onBackground = DarkText,
    
    outline = DarkOutline,
    outlineVariant = DarkOutline.copy(alpha = 0.5f),
    
    error = DarkError,
    onError = Color.White,
    errorContainer = DarkErrorBg,
    onErrorContainer = DarkError,
    
    scrim = Color.Black.copy(alpha = 0.5f)
)

@Composable
fun DocumentScannerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}

val MaterialTheme.customColors: CustomColors
    @Composable
    get() = if (isSystemInDarkTheme()) {
        CustomColors(
            cardBg = DarkCardBg,
            chipBg = DarkChipBg,
            fieldBg = DarkFieldBg,
            divider = DarkDivider,
            textSecondary = DarkTextSecondary
        )
    } else {
        CustomColors(
            cardBg = LightCardBg,
            chipBg = LightChipBg,
            fieldBg = LightFieldBg,
            divider = LightDivider,
            textSecondary = LightTextSecondary
        )
    }

data class CustomColors(
    val cardBg: Color,
    val chipBg: Color,
    val fieldBg: Color,
    val divider: Color,
    val textSecondary: Color
)
