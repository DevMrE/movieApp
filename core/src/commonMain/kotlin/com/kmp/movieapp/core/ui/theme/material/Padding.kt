package com.kmp.movieapp.core.ui.theme.material

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Padding(
    val veryTiny: Dp = 2.dp,
    val tiny: Dp = 5.dp,
    val six: Dp = 6.dp,
    val ten: Dp = 10.dp,
    val twelfth: Dp = 12.dp,
    val sixteen: Dp = 16.dp,
    val twenty: Dp = 20.dp,
    val twentyFive: Dp = 25.dp,
    val thirty: Dp = 30.dp,
    val thirtySix: Dp = 36.dp,
    val sixty: Dp = 60.dp,
    val eighty: Dp = 80.dp,
    val oneHundredTwenty: Dp = 120.dp,
    val defaultContentPadding: Dp = 14.dp
)

private val LocalPadding = staticCompositionLocalOf { Padding() }

val MaterialTheme.padding: Padding
    @Composable
    @ReadOnlyComposable
    get() = LocalPadding.current