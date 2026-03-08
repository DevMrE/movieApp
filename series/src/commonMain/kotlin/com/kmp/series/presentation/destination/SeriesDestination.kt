package com.kmp.series.presentation.destination

import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data object SeriesDestination : NavDestination


@Serializable
data class SeriesDetailDestination(
    val id: String
) : NavDestination