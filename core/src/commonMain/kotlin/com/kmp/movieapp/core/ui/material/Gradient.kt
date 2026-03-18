package com.kmp.movieapp.core.ui.material

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
            Color.Black.copy(alpha = 0.4f),
            Color.Black.copy(0.9f),
        )
    )
)

private val LocalGradient = staticCompositionLocalOf { Gradient() }

// 3. Extension-Property auf MaterialTheme
val MaterialTheme.gradient: Gradient
    @Composable
    @ReadOnlyComposable
    get() = LocalGradient.current