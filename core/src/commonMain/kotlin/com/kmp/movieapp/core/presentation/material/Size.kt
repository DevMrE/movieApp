package com.kmp.movieapp.core.presentation.material

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Size(
    val bottomBarStrokeHeight: Dp = 1.dp,
    val movieCardHeight: Dp = 220.dp,
    val movieCardWidth: Dp = 150.dp,
    val moviePosterWidth: Dp = 350.dp,
    val bottomBarHeight: Dp = 80.dp
)

private val LocalSize = staticCompositionLocalOf { Size() }

val MaterialTheme.size: Size
    @Composable
    @ReadOnlyComposable
    get() = LocalSize.current