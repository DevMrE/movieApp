package com.kmp.movieapp.app_bar.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kmp.movieapp.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.app_name
import com.kmp.movieapp.composeApp.discover_media_title
import com.kmp.movieapp.composeApp.more
import com.kmp.movieapp.composeApp.popular_movies_title
import com.kmp.movieapp.composeApp.popular_series_title
import com.kmp.movieapp.composeApp.trending_title
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.Route
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun TopAppBarComponent() {

    val navigator: Navigator<Route> = koinInject()
    val backStack = navigator.backStack.lastOrNull()

    val showBack = when (backStack) {
        is HomeNavigation.SeeAllRoute -> true
        else -> false
    }

    val screenStringResource = when (backStack) {
        AppNavigation.More -> Res.string.more
        AppNavigation.Browse -> Res.string.discover_media_title
        else -> Res.string.app_name
    }

    val title = when (backStack) {
        is HomeNavigation.ContentDetail -> {
            val currentTitle = when (backStack.mediaCategory) {
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