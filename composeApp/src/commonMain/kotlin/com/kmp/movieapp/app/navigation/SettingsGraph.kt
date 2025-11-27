package com.kmp.movieapp.app.navigation

import com.kmp.movieapp.settings.SettingsScreenComponent
import com.kmp.movieapp.settings.destination.SettingsScreenDestination
import com.kmp.navigation.TypedGraphBuilder
fun TypedGraphBuilder.settingsGraph() {
    screen<SettingsScreenDestination> {
        SettingsScreenComponent()
    }
}