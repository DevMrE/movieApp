package com.kmp.movieapp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.presentation.theme.AppTheme
import com.kmp.movieapp.movie.presentation.MovieScreenComponent
import com.kmp.movieapp.movie.presentation.route.MovieScreenDestination
import com.kmp.navigation.compose_interface.RegisterNavigation
import com.kmp.navigation.compose_interface.navGraph
import com.kmp.navigation.destination.SeriesScreenDestination

@Composable
fun MovieAppComponent() {
    AppTheme {
        Scaffold { paddingValues ->
            RegisterNavigation(
                startNavDestination = MovieScreenDestination,
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                navGraph {
                    screen<MovieScreenDestination> { navParameter ->
                        MovieScreenComponent()
                    }

                    screen<SeriesScreenDestination> { data ->

                    }
                }
            }
        }
    }
}