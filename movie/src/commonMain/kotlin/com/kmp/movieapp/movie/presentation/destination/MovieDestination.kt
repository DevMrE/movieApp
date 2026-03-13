package com.kmp.movieapp.movie.presentation.destination

import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data object MovieContentDestination : NavDestination

@Serializable
data class MovieCategoryListDestination(
    val movieCategory: MovieCategory
) : NavDestination
