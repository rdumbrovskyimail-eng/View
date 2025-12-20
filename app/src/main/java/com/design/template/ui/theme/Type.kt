package com.design.template.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(

    titleLarge = TextStyle(
        fontSize = 22.sp,                // Чуть больше для элегантности
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.15.sp
    ),

    titleSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.5.sp
    ),

    bodySmall = TextStyle(
        fontSize = 13.sp,
        lineHeight = 18.sp               // Больше пространства для читаемости
    ),

    labelLarge = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium
    ),

    labelSmall = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.5.sp
    )
)
