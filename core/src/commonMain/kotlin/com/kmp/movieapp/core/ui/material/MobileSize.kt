package com.kmp.movieapp.core.ui.material

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class MobileSize(
    val bottomBarStrokeHeight: Dp = 1.dp,
    val borderStroke: Dp = 2.dp,
    val voteAverageIconSize: Dp = 20.dp,
    val iconSize: Dp = 24.dp,
    val defaultCardHeight: Dp = 220.dp,
    val defaultCardListHeight: Dp = 180.dp,
    val defaultCardWidth: Dp = 150.dp,
    val movieBigCardWidth: Dp = 350.dp,
    val bottomBarHeight: Dp = 80.dp,
    val detailHeaderHeight: Dp = 500.dp,
    val customShapeSize: Dp = 6.dp,
    val shapeOffset: Dp = 4.dp
)

private val LocalMobileSize = staticCompositionLocalOf { MobileSize() }

val MaterialTheme.mobileSize: MobileSize
    @Composable
    @ReadOnlyComposable
    get() = LocalMobileSize.current