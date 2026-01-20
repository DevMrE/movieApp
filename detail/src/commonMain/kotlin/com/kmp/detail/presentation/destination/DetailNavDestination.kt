package com.kmp.detail.presentation.destination

import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class DetailNavDestination(
    val movieId: Int
): NavDestination
