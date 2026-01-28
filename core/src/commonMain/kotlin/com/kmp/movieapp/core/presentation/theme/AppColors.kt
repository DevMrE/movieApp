package com.kmp.movieapp.core.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// LIGHT THEME aus deinen CSS-Variablen
val LightColorScheme = lightColorScheme(
    primary = Color(0xFF395886),
    onPrimary = Color(0xFFFFFFFF),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF212121),

    surface = Color(0xFFF5F5F5),
    onSurface = Color(0xFF212121),
    surfaceContainer = Color(0xd5e8e8e8),

    surfaceVariant = Color(0xE4BBBBBB),
    onSurfaceVariant = Color(0xFF212121),

    tertiary = Color(0xFF03DAC6),
    outline = Color(0xFF757575),
)

// DARK THEME aus deinen CSS-Variablen
val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF03DAC6),
    onPrimary = Color(0xFF000000),

    background = Color(0xFF121212),
    onBackground = Color(0xFFE0E0E0),

    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),
    surfaceContainer = Color(0xFF2C2C2C),

    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = Color(0xFFE0E0E0),

    tertiary = Color(0xFF395886),
    outline = Color(0xFFB0B0B0),
)
