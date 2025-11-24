package com.kmp.movieapp.core.presentation.material

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Padding(
    val five: Dp = 5.dp,
    val ten: Dp = 10.dp,
    val fifteen: Dp = 15.dp,
    val twenty: Dp = 20.dp,
    val twentyFive: Dp = 25.dp,
    val thirty: Dp = 30.dp
)

private val LocalPadding = staticCompositionLocalOf { Padding() }

val MaterialTheme.padding: Padding
    @Composable
    @ReadOnlyComposable
    get() = LocalPadding.current