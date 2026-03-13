package com.kmp.movieapp.core.presentation.navigation

import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class MediaDetailDestination(
    val title: String
) : NavDestination