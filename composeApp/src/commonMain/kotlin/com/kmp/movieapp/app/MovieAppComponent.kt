package com.kmp.movieapp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.movieapp.app.bottombar.BottomBarComponent
import com.kmp.movieapp.app.navigation.homeGraph
import com.kmp.movieapp.app.navigation.settingsGraph
import com.kmp.movieapp.app.topbar.TopAppBarComponent
import com.kmp.movieapp.core.presentation.theme.AppTheme
import com.kmp.movieapp.homescreen.destination.HomeScreenDestination
import com.kmp.navigation.compose_interface.RegisterNavigation
import com.kmp.navigation.compose_interface.navGraph

@Composable
fun MovieAppComponent() {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBarComponent()
            },
            bottomBar = {
                BottomBarComponent()
            }
        ) { paddingValues ->
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
    }
}