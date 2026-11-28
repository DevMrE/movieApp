package com.kmp.movieapp.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val vynoraColors = if (darkTheme) DarkColors else LightColors
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalVynoraColors provides vynoraColors,
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = appTypography(),
            shapes = AppShapes,
            content = content
        )
    }
}
