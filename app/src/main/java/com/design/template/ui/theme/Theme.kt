package com.yourapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    surface = LightSurface,
    background = LightBackground,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = LightText,
    onBackground = LightText,
    outline = LightOutline,
    error = LightError,
    errorContainer = LightErrorBg
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    surface = DarkSurface,
    background = DarkBackground,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = DarkText,
    onBackground = DarkText,
    outline = DarkOutline,
    error = DarkError,
    errorContainer = DarkErrorBg
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
        content = content
    )
}

// Extension для доступа к кастомным цветам
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
