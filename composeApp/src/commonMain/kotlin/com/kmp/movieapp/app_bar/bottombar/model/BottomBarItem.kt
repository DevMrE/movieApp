package com.kmp.movieapp.app_bar.bottombar.model

import com.kmp.navigation.NavDestination
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class BottomBarItem(
    val icon: DrawableResource,
    val navDestination: NavDestination,
    val label: StringResource? = null
)
