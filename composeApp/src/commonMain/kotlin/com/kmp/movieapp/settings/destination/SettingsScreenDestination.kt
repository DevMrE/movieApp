package com.kmp.movieapp.settings.destination

import com.kmp.navigation.NavDestination
import com.kmp.navigation.NavSection
import kotlinx.serialization.Serializable

@Serializable
data object SettingsSection : NavSection

@Serializable
data object SettingsScreenDestination : NavDestination