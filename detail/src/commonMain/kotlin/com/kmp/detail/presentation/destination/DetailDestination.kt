package com.kmp.detail.presentation.destination

import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class DetailDestination(
    val id: String
) : NavDestination
