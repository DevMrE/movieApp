package com.kmp.movieapp.homescreen.model

import androidx.compose.runtime.Stable
import com.kmp.navigation.NavDestination
import org.jetbrains.compose.resources.StringResource

@Stable
data class UiTabState(
    val tabResource: StringResource,
    val destination: NavDestination
)
