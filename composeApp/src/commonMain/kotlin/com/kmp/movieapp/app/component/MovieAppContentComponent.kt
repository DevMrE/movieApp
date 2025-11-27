package com.kmp.movieapp.app.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.movieapp.app.navigation.homeGraph
import com.kmp.movieapp.app.navigation.settingsGraph
import com.kmp.movieapp.homescreen.destination.HomeScreenDestination
import com.kmp.navigation.compose.RegisterNavigation
import com.kmp.navigation.navGraph

@Composable
fun MovieAppContentComponent(paddingValues: PaddingValues) {
    RegisterNavigation(
        startNavDestination = HomeScreenDestination,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        navGraph {
            homeGraph()
            settingsGraph()
        }
    }
}