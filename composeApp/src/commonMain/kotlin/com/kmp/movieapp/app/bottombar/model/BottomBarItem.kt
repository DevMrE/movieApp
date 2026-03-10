package com.kmp.movieapp.app.bottombar.model

import com.kmp.navigation.NavDestination
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class BottomBarItem(
    val icon: DrawableResource,
    val label: StringResource,
    val navDestination: NavDestination
)
