package com.kmp.movieapp.core.ui.navigation

import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class MediaDetailDestination(
    val title: String
) : NavDestination