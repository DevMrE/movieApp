package com.kmp.movieapp.app.navigation

import com.kmp.kmpnavigation.compose_interface.TypedGraphBuilder
import com.kmp.movieapp.settings.SettingsScreenComponent
import com.kmp.movieapp.settings.destination.SettingsScreenDestination

fun TypedGraphBuilder.settingsGraph() {
    screen<SettingsScreenDestination> {
        SettingsScreenComponent()
    }
}