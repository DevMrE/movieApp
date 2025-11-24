package com.kmp.movieapp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.presentation.theme.AppTheme
import com.kmp.movieapp.homescreen.HomeScreenComponent
import com.kmp.movieapp.homescreen.destination.HomeScreenDestination
import com.kmp.navigation.compose_interface.RegisterNavigation
import com.kmp.navigation.compose_interface.navGraph

@Composable
fun MovieAppComponent() {
    AppTheme {
        Scaffold(
            bottomBar = {

            }
        ) { paddingValues ->
            RegisterNavigation(
                startNavDestination = HomeScreenDestination,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                navGraph {
                    screen<HomeScreenDestination> {
                        HomeScreenComponent()
                    }
                }
            }
        }
    }
}