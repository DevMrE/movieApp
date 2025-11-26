package com.kmp.series.presentation.destination

import com.kmp.kmpnavigation.util.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class SeriesDetailScreenDestination(val id: Int) : NavDestination