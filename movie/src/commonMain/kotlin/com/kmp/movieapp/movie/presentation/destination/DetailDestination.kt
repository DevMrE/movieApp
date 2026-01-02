package com.kmp.movieapp.movie.presentation.destination

import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class DetailDestination(
    val id: Int
) : NavDestination