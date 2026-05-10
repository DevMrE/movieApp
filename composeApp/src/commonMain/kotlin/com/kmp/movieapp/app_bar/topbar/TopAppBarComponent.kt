package com.kmp.movieapp.app_bar.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kmp.movieapp.app_bar.bottombar.destination.BottomBarTabs
import com.kmp.movieapp.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.app_name
import com.kmp.movieapp.composeApp.discover_media_title
import com.kmp.movieapp.composeApp.more
import com.kmp.movieapp.composeApp.popular_movies_title
import com.kmp.movieapp.composeApp.popular_series_title
import com.kmp.movieapp.composeApp.trending_title
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.discover.presentation.destination.DiscoverMediaDestination
import com.kmp.movieapp.homescreen.presentation.destination.HomeMediaCategoryListDestination
import com.kmp.movieapp.settings.destination.SettingsDestination
import com.kmp.navigation.compose.rememberActiveTabIn
import com.kmp.navigation.compose.rememberNavDestination
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopAppBarComponent() {
    val navSection = rememberActiveTabIn<BottomBarTabs>()
    val navDestination = rememberNavDestination()

    val showBack = when (navDestination) {
        is HomeMediaCategoryListDestination -> true
        else -> false
    }

    val screenStringResource = when (navSection) {
        SettingsDestination -> Res.string.more
        DiscoverMediaDestination -> Res.string.discover_media_title
        else -> Res.string.app_name
    }

    val title = when (navDestination) {
        is HomeMediaCategoryListDestination -> {
            val currentTitle = when (navDestination.mediaCategory) {
                MediaCategory.MOVIE -> Res.string.popular_movies_title
                MediaCategory.SERIES -> Res.string.popular_series_title
                else -> Res.string.trending_title
            }

            stringResource(currentTitle)
        }

        else -> stringResource(screenStringResource)
    }

    TopAppBarContent(
        title = title,
        showBackButton = showBack
    )
}

@Preview
@Composable
private fun TopAppBarComponentPreview() {
    TopAppBarContent(
        title = "MovieApp",
        showBackButton = true
    )
}