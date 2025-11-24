package com.kmp.movieapp.core.presentation.material

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class Gradient(
    val card: Brush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color(0xFF212121).copy(alpha = 1f)
        )
    )
)

private val LocalGradient = staticCompositionLocalOf { Gradient() }

// 3. Extension-Property auf MaterialTheme
val MaterialTheme.gradient: Gradient
    @Composable
    @ReadOnlyComposable
    get() = LocalGradient.current