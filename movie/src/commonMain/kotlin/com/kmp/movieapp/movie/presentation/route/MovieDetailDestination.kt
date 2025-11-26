package com.kmp.movieapp.movie.presentation.route

import com.kmp.kmpnavigation.util.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDestination(
    val id: Int
) : NavDestination