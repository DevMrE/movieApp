package com.kmp.movieapp.app.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import co.touchlab.kermit.Logger
import com.kmp.movieapp.app.navigation.destination.BottomBarTabs
import com.kmp.movieapp.app.navigation.destination.DiscoverMoviesDestination
import com.kmp.movieapp.app.topbar.component.TopAppBarContent
import com.kmp.movieapp.movie.presentation.destination.MovieCategoryListDestination
import com.kmp.movieapp.settings.destination.SettingsDestination
import com.kmp.navigation.compose.rememberActiveTabIn
import com.kmp.navigation.compose.rememberNavDestination
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.app_name
import movieapp.composeapp.generated.resources.discover_movies_title
import movieapp.composeapp.generated.resources.settings_screen_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopAppBarComponent() {
    val navSection = rememberActiveTabIn<BottomBarTabs>()
    val navDestination = rememberNavDestination()
    val showBack = when (navDestination) {
        is MovieCategoryListDestination -> true
        else -> false
    }

    val screenStringResource = when (navSection) {
        SettingsDestination -> Res.string.settings_screen_title
        DiscoverMoviesDestination -> Res.string.discover_movies_title
        else -> Res.string.app_name
    }


    Logger.i { "KMPNavi - showBackButton?: $showBack" }
    TopAppBarContent(
        title = stringResource(screenStringResource),
        showBackButton = showBack
    )
}

@Preview
@Composable
private fun TopAppBarComponentPreview() {
    TopAppBarContent(title = "MovieApp", showBackButton = true)
}