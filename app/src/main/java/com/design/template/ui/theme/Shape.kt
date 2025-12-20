package com.design.template.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Modern 2025-2026: Более закругленные формы
val Shapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),    // Было 4dp
    small = RoundedCornerShape(12.dp),        // Было 8dp
    medium = RoundedCornerShape(16.dp),       // Было 12dp
    large = RoundedCornerShape(20.dp),        // Было 16dp
    extraLarge = RoundedCornerShape(32.dp)    // Было 28dp
)
