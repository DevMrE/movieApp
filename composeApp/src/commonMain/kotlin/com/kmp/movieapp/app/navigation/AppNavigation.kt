package com.kmp.movieapp.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.homescreen.HomeScreen
import com.kmp.movieapp.homescreen.destination.HomeScreenDestination
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.movie.presentation.MovieScreen
import com.kmp.movieapp.movie.presentation.destination.DetailDestination
import com.kmp.movieapp.movie.presentation.destination.MovieScreenDestination
import com.kmp.movieapp.movie.presentation.destination.MovieScreenSection
import com.kmp.movieapp.settings.destination.SettingsScreenDestination
import com.kmp.movieapp.settings.destination.SettingsSection
import com.kmp.navigation.ScreenRole
import com.kmp.navigation.registerNavigation
import com.kmp.series.presentation.SeriesScreen
import com.kmp.series.presentation.destination.SeriesScreenDestination
import com.kmp.series.presentation.destination.SeriesScreenSection

fun registerAppNavigation() {
    registerNavigation(
        startDestination = HomeScreenDestination,
        screenStrategies = {}
    ) {

        // The start point is the HomeScreenSection and is therefore the root section of this app.
        section(section = HomeScreenSection, root = HomeScreenDestination) {
            screen<HomeScreenDestination> {
                HomeScreen()
            }

            section(section = MovieScreenSection, root = MovieScreenDestination) {
                screen<MovieScreenDestination> {
                    MovieScreen()
                }
            }

            section(SeriesScreenSection, root = SeriesScreenDestination) {
                screen<SeriesScreenDestination> {
                    SeriesScreen()
                }
            }

            screen<DetailDestination>(role = ScreenRole.Detail) { detail ->
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        Text("Detail for: ${detail.id}", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }

        section(section = SettingsSection, root = SettingsScreenDestination) {
            screen<SettingsScreenDestination> {
                Scaffold {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text("Settings baby")
                    }
                }
            }
        }
    }
}