package com.kmp.movieapp.core.ui.material

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import com.kmp.movieapp.core.ui.theme.DarkColorScheme
import com.kmp.movieapp.core.ui.theme.LightColorScheme

data class Gradient(
    val card: Brush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Transparent,
            Color.Black.copy(alpha = 0.4f),
            Color.Black.copy(0.9f),
        )
    ),

    val detailCardDarkMode: Brush = Brush.verticalGradient(
        colors = listOf(
            DarkColorScheme.background.copy(alpha = 0.7f),
            DarkColorScheme.background.copy(alpha = 0.7f),
            DarkColorScheme.background.copy(alpha = 0.7f),
            DarkColorScheme.background.copy(alpha = 0.7f),
            DarkColorScheme.background.copy(alpha = 0.9f),
            DarkColorScheme.background.copy(alpha = 1f),
            DarkColorScheme.background.copy(alpha = 1f),
        )
    ),

    val detailCardLightMode: Brush = Brush.verticalGradient(
        colors = listOf(
            LightColorScheme.background.copy(alpha = 0.1f),
            LightColorScheme.background.copy(alpha = 0.2f),
            LightColorScheme.background.copy(alpha = 0.3f),
            LightColorScheme.background.copy(alpha = 0.5f),
            LightColorScheme.background.copy(alpha = 0.8f),
            LightColorScheme.background.copy(alpha = 1f),
            LightColorScheme.background.copy(alpha = 1f),
        ),
        tileMode = TileMode.Decal
    ),
)

private val LocalGradient = staticCompositionLocalOf { Gradient() }

// 3. Extension-Property auf MaterialTheme
val MaterialTheme.gradient: Gradient
    @Composable
    @ReadOnlyComposable
    get() = LocalGradient.current