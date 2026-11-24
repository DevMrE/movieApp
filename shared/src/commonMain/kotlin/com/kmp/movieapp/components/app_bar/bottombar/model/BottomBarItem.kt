package com.kmp.movieapp.components.app_bar.bottombar.model

import com.kmp.movieapp.components.app_bar.bottombar.navigation.BottomBarRoute
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class BottomBarItem(
    val icon: DrawableResource,
    val navDestination: BottomBarRoute,
    val label: StringResource? = null
)
