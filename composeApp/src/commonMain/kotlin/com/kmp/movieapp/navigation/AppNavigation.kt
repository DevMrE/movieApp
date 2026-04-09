package com.kmp.movieapp.navigation

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import com.kmp.movieapp.app_bar.bottombar.destination.BottomBarTabs
import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.core.ui.navigation.MediaDetailDestination
import com.kmp.movieapp.discover.presentation.DiscoverMediaScreen
import com.kmp.movieapp.discover.presentation.destination.DiscoverMediaDestination
import com.kmp.movieapp.homescreen.HomeContent
import com.kmp.movieapp.homescreen.destination.HomeDestination
import com.kmp.movieapp.homescreen.destination.HomeTabs
import com.kmp.movieapp.movie.presentation.MovieContent
import com.kmp.movieapp.movie.presentation.destination.MovieCategoryListDestination
import com.kmp.movieapp.movie.presentation.destination.MovieContentDestination
import com.kmp.movieapp.movie.presentation.movie_list_category.MovieCategoryListScreen
import com.kmp.movieapp.search.presentation.SearchContent
import com.kmp.movieapp.search.presentation.destination.SearchScreenDestination
import com.kmp.movieapp.settings.SettingsContent
import com.kmp.movieapp.settings.destination.SettingsDestination
import com.kmp.navigation.registerNavigation
import com.kmp.series.presentation.SeriesContent
import com.kmp.series.presentation.destination.SeriesContentDestination

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

        content<DiscoverMediaDestination> {
            DiscoverMediaScreen()
        }

        screen<MediaDetailDestination> { dest ->
            ContentDetailScreen(
                id = dest.id,
                contentType = dest.contentDetailType
            )
        }

        content<SearchScreenDestination> {
            SearchContent()
        }

        tabs<HomeTabs>(
            startDestination = MovieContentDestination,
            MovieContentDestination, SeriesContentDestination
        )

        tabs<BottomBarTabs>(
            startDestination = HomeDestination,
            HomeDestination, SettingsDestination, DiscoverMediaDestination
        )
    }
}