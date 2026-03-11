package com.kmp.movieapp.app.navigation

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kmp.movieapp.app.navigation.destination.BottomBarTabs
import com.kmp.movieapp.app.navigation.destination.DiscoverMoviesDestination
import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.homescreen.HomeContent
import com.kmp.movieapp.homescreen.destination.HomeDestination
import com.kmp.movieapp.homescreen.destination.HomeTabs
import com.kmp.movieapp.movie.presentation.MovieContent
import com.kmp.movieapp.movie.presentation.destination.MovieCategoryListDestination
import com.kmp.movieapp.movie.presentation.destination.MovieContentDestination
import com.kmp.movieapp.movie.presentation.destination.MovieDetailDestination
import com.kmp.movieapp.movie.presentation.movie_list_category.MovieCategoryListScreen
import com.kmp.movieapp.settings.SettingsContent
import com.kmp.movieapp.settings.destination.SettingsDestination
import com.kmp.navigation.registerNavigation
import com.kmp.series.presentation.SeriesContent
import com.kmp.series.presentation.destination.SeriesContentDestination
import com.kmp.series.presentation.destination.SeriesDetailDestination

fun registerAppNavigation() {
    registerNavigation(startDestination = HomeDestination) {
        content<HomeDestination> { HomeContent() }
        content<SettingsDestination> { SettingsContent() }
        content<MovieContentDestination> { MovieContent() }
        content<SeriesContentDestination> { SeriesContent() }
        content<MovieCategoryListDestination>(
            enterTransition = {
                scaleIn()
            },
            exitTransition = {
                scaleOut()
            }
        ) { data -> MovieCategoryListScreen(data.movieCategory) }

        content<DiscoverMoviesDestination> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Text("Another Movie List", color = Color.White)
            }
        }

        screen<MovieDetailDestination> { dest ->
            ContentDetailScreen(dest.id)
        }

        screen<SeriesDetailDestination> { dest ->
            ContentDetailScreen(dest.id)
        }

        tabs<HomeTabs>(
            startDestination = MovieContentDestination,
            MovieContentDestination, SeriesContentDestination
        )

        tabs<BottomBarTabs>(
            startDestination = HomeDestination,
            HomeDestination, SettingsDestination, DiscoverMoviesDestination
        )
    }
}