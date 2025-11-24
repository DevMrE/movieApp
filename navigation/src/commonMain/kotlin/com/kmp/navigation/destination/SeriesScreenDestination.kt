package com.kmp.navigation.destination

import com.kmp.navigation.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class SeriesScreenDestination(val text: String) : NavDestination