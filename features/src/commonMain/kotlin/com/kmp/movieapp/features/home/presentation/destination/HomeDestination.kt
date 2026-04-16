package com.kmp.movieapp.features.home.presentation.destination

import com.kmp.movieapp.features.home.domain.model.HomeCategory
import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class HomeMediaCategoryListDestination(
    val homeCategory: HomeCategory
) : NavDestination
