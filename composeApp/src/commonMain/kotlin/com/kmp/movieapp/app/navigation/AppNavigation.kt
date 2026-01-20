package com.kmp.movieapp.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.kmp.detail.presentation.DetailScreen
import com.kmp.detail.presentation.destination.DetailNavDestination
import com.kmp.movieapp.homescreen.HomeScreen
import com.kmp.movieapp.homescreen.destination.HomeScreenDestination
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.movie.presentation.MovieScreen
import com.kmp.movieapp.movie.presentation.content.destination.PopularMovieDestination
import com.kmp.movieapp.movie.presentation.destination.MovieScreenDestination
import com.kmp.movieapp.movie.presentation.destination.MovieScreenSection
import com.kmp.movieapp.movie.presentation.movieList.PopularMovieListScreen
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

            screen<DetailNavDestination>(role = ScreenRole.Detail) { detail ->
                DetailScreen(detail.movieId)
            }

            screen<PopularMovieDestination> {
                PopularMovieListScreen()
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