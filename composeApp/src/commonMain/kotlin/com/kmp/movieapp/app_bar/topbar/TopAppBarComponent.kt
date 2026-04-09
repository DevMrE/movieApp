package com.kmp.movieapp.app_bar.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kmp.movieapp.app_bar.bottombar.destination.BottomBarTabs
import com.kmp.movieapp.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.app_name
import com.kmp.movieapp.composeApp.discover_media_title
import com.kmp.movieapp.composeApp.more
import com.kmp.movieapp.composeApp.movie_category_list_title
import com.kmp.movieapp.composeApp.movie_category_now_playing
import com.kmp.movieapp.composeApp.movie_category_popular
import com.kmp.movieapp.composeApp.movie_category_top_rated
import com.kmp.movieapp.discover.presentation.destination.DiscoverMediaDestination
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.presentation.destination.MovieCategoryListDestination
import com.kmp.movieapp.settings.destination.SettingsDestination
import com.kmp.navigation.compose.rememberActiveTabIn
import com.kmp.navigation.compose.rememberNavDestination
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
        SettingsDestination -> Res.string.more
        DiscoverMediaDestination -> Res.string.discover_media_title
        else -> Res.string.app_name
    }

    val title = when (navDestination) {
        is MovieCategoryListDestination -> {
            val category = when (navDestination.movieCategory) {
                MovieCategory.TOP_RATED -> stringResource(Res.string.movie_category_top_rated)
                MovieCategory.NOW_PLAYING -> stringResource(Res.string.movie_category_now_playing)
                MovieCategory.POPULAR -> stringResource(Res.string.movie_category_popular)
            }

            stringResource(Res.string.movie_category_list_title, category)
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