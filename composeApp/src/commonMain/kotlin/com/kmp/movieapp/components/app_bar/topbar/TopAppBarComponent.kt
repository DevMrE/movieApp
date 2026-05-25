package com.kmp.movieapp.components.app_bar.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.components.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.app_name
import com.kmp.movieapp.composeApp.discover_media_title
import com.kmp.movieapp.composeApp.more
import com.kmp.movieapp.composeApp.popular_movies_title
import com.kmp.movieapp.composeApp.popular_series_title
import com.kmp.movieapp.composeApp.trending_title
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import com.kmp.movieapp.core.util.navigation.util.koinNavigation
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopAppBarComponent() {

    val navigator: Navigator<HomeNavigation> = koinNavigation()
    val backStack = navigator.backStack.lastOrNull()

    val showTopBar = when (backStack) {
        is HomeNavigation.ContentDetailRoute -> false
        else -> true
    }

    val showBack = when (backStack) {
        is HomeNavigation.SeeAllRoute -> true
        else -> false
    }

    val screenStringResource = when (backStack) {
        AppNavigation.MoreRoute -> Res.string.more
        AppNavigation.BrowseRoute -> Res.string.discover_media_title
        else -> Res.string.app_name
    }

    val title = when (backStack) {
        is HomeNavigation.ContentDetailRoute -> {
            val currentTitle = when (backStack.mediaCategory) {
                MediaCategory.MOVIE -> Res.string.popular_movies_title
                MediaCategory.SERIES -> Res.string.popular_series_title
                else -> Res.string.trending_title
            }

            stringResource(currentTitle)
        }

        else -> stringResource(screenStringResource)
    }

    AnimatedVisibility(
        visible = showTopBar.isTrue,
        modifier = Modifier.systemBarsPadding()
    ) {
        TopAppBarContent(
            title = title,
            showBackButton = showBack
        )
    }
}

@PreviewLightDark
@Composable
private fun TopAppBarComponentPreview() {
    TopAppBarContent(
        title = "MovieApp",
        showBackButton = true
    )
}