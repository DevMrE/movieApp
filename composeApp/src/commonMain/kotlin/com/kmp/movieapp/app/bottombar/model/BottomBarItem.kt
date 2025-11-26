package com.kmp.movieapp.app.bottombar.model

import com.kmp.kmpnavigation.util.NavDestination
import org.jetbrains.compose.resources.DrawableResource

data class BottomBarItem(
    val icon: DrawableResource,
    val navDestination: NavDestination
)
