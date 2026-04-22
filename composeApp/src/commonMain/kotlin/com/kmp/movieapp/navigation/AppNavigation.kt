package com.kmp.movieapp.navigation

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import com.kmp.movieapp.app_bar.bottombar.destination.BottomBarTabs
import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.core.ui.navigation.MediaDetailDestination
import com.kmp.movieapp.discover.presentation.DiscoverMediaScreen
import com.kmp.movieapp.discover.presentation.destination.DiscoverMediaDestination
import com.kmp.movieapp.features.home.presentation.HomeContent
import com.kmp.movieapp.features.home.presentation.destination.HomeMediaCategoryListDestination
import com.kmp.movieapp.features.media_list.presentation.MediaListScreen
import com.kmp.movieapp.homescreen.destination.HomeDestination
import com.kmp.movieapp.search.presentation.SearchContent
import com.kmp.movieapp.search.presentation.destination.SearchScreenDestination
import com.kmp.movieapp.settings.SettingsContent
import com.kmp.movieapp.settings.destination.SettingsDestination
import com.kmp.navigation.registerNavigation

fun registerAppNavigation() {
    registerNavigation(startDestination = HomeDestination) {
        content<HomeDestination> { HomeContent() }
        content<SettingsDestination> { SettingsContent() }
        content<HomeMediaCategoryListDestination>(
            enterTransition = {
                scaleIn()
            },
            exitTransition = {
                scaleOut()
            }
        ) { data -> MediaListScreen(data.homeCategory) }

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

        tabs<BottomBarTabs>(
            startDestination = HomeDestination,
            HomeDestination, SettingsDestination, DiscoverMediaDestination
        )
    }
}