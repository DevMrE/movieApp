package com.kmp.movieapp.core.ui.theme.material.size

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

interface Size {
    val voteAverageIconSize: Dp
    val defaultCardHeight: Dp
    val defaultCardListHeight: Dp
    val defaultCardWidth: Dp
    val movieBigCardWidth: Dp
    val bottomBarHeight: Dp
    val imageSize: Dp
}

private fun getSize(): Size {
    // TODO: Add here a when expression and choose which
    // Size should be loaded depending on screen size.
    return MobileSize()
}

private val LocalSize = staticCompositionLocalOf { getSize() }

val MaterialTheme.size: Size
    @Composable
    @ReadOnlyComposable
    get() = LocalSize.current