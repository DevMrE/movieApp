package com.kmp.movieapp.movie.presentation.destination

import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data object MovieDestination : NavDestination

@Serializable
data class MovieDetailDestination(
    val id: String
) : NavDestination