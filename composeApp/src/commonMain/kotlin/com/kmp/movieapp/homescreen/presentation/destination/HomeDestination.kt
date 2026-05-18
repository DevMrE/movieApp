package com.kmp.movieapp.homescreen.presentation.destination

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class HomeMediaListDestination(
    val mediaCategory: MediaCategory
) : NavDestination
