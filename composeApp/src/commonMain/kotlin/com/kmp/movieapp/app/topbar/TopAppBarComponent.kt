package com.kmp.movieapp.app.topbar

import androidx.compose.runtime.Composable
import com.kmp.movieapp.app.topbar.component.TopAppBarContent
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.movie.presentation.destination.MovieDetailDestination
import com.kmp.movieapp.settings.destination.SettingsScreenDestination
import com.kmp.navigation.compose.rememberNavDestination
import com.kmp.series.presentation.destination.SeriesDetailScreenDestination
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.app_name
import movieapp.composeapp.generated.resources.settings_screen_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopAppBarComponent() {
    val navDestination = rememberNavDestination()

    val screenStringResource = when (navDestination) {
        is SettingsScreenDestination -> Res.string.settings_screen_title
        else -> Res.string.app_name
    }

    val enableBackNavigation = when (navDestination) {
        is MovieDetailDestination,
        is SeriesDetailScreenDestination -> false

        else -> true
    }

    if (enableBackNavigation.isTrue) TopAppBarContent(title = stringResource(screenStringResource))
}