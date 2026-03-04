package com.kmp.movieapp.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kmp.detail.presentation.DetailScreen
import com.kmp.detail.presentation.destination.DetailDestination
import com.kmp.movieapp.app.navigation.destination.BottomBarTabs
import com.kmp.movieapp.app.navigation.destination.DiscoverMoviesDestination
import com.kmp.movieapp.homescreen.HomeContent
import com.kmp.movieapp.homescreen.destination.HomeDestination
import com.kmp.movieapp.homescreen.destination.HomeTabs
import com.kmp.movieapp.movie.presentation.MovieContent
import com.kmp.movieapp.movie.presentation.content.destination.PopularMovieDestination
import com.kmp.movieapp.movie.presentation.destination.MovieDestination
import com.kmp.movieapp.movie.presentation.movieList.PopularMovieListScreen
import com.kmp.movieapp.settings.SettingsContent
import com.kmp.movieapp.settings.destination.SettingsDestination
import com.kmp.navigation.registerNavigation
import com.kmp.series.presentation.SeriesContent
import com.kmp.series.presentation.destination.SeriesDestination

fun registerAppNavigation() {
    registerNavigation(startDestination = HomeDestination) {
        content<HomeDestination> { HomeContent() }
        content<SettingsDestination> { SettingsContent() }
        content<MovieDestination> { MovieContent() }
        content<SeriesDestination> { SeriesContent() }
        content<PopularMovieDestination> { PopularMovieListScreen() }

        content<DiscoverMoviesDestination> {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(Color.Black)
            ) {
                Text("Another Movie List", color = Color.White)
            }
        }

        screen<DetailDestination> { dest -> DetailScreen(dest.id) }

        tabs<HomeTabs>(
            startDestination = MovieDestination,
            MovieDestination, SeriesDestination
        )

        tabs<BottomBarTabs>(
            startDestination = HomeDestination,
            HomeDestination, SettingsDestination, DiscoverMoviesDestination
        )
    }
}