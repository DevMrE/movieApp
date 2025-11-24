package com.kmp.movieapp.movie.presentation.route

import com.kmp.navigation.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDestination(
    val id: Int
) : NavDestination