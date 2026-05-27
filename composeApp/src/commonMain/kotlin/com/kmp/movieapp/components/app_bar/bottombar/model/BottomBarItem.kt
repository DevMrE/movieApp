package com.kmp.movieapp.components.app_bar.bottombar.model

import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class BottomBarItem(
    val icon: DrawableResource,
    val navDestination: AppNavigation,
    val label: StringResource? = null
)
