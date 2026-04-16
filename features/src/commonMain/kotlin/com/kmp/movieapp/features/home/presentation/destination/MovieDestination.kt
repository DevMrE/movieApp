package com.kmp.movieapp.features.home.presentation.destination

import com.kmp.movieapp.features.home.domain.model.MovieCategory
import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data object MovieContentDestination : NavDestination

@Serializable
data class MovieCategoryListDestination(
    val movieCategory: MovieCategory
) : NavDestination
