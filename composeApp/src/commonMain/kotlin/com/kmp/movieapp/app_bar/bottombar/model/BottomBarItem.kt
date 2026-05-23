package com.kmp.movieapp.app_bar.bottombar.model

import com.kmp.movieapp.core.util.navigation.Route
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class BottomBarItem(
    val icon: DrawableResource,
    val navDestination: Route,
    val label: StringResource? = null
)
