package com.kmp.movieapp.core.ui.theme.material.size

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class MobileSize(
    override val voteAverageIconSize: Dp = 20.dp,
    override val defaultCardHeight: Dp = 220.dp,
    override val defaultCardListHeight: Dp = 180.dp,
    override val defaultCardWidth: Dp = 150.dp,
    override val movieBigCardWidth: Dp = 350.dp,
    override val bottomBarHeight: Dp = 80.dp,
    override val imageSize: Dp = 600.dp
) : Size
