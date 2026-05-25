package com.kmp.movieapp.overview

import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.components.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.popular_movies_title
import com.kmp.movieapp.composeApp.popular_series_title
import com.kmp.movieapp.core.ui.container.GridContainer
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import com.kmp.movieapp.core.util.navigation.util.koinNavigation
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalGridApi::class)
@Composable
fun OverviewListScreen(mediaCategory: MediaCategory) {

    val viewModel = koinViewModel<OverviewListViewModel>(
        key = mediaCategory.name,
        parameters = { parametersOf(mediaCategory) }
    )

    val movieList = viewModel.movieListState.collectAsStateWithLifecycle()
    val navigator = koinNavigation<HomeNavigation>()
    val backStack = navigator.getCurrentBackStack()
    val titleRes = when (mediaCategory) {
        MediaCategory.SERIES -> Res.string.popular_series_title
        else -> Res.string.popular_movies_title
    }


    Scaffold(
        topBar = {
            TopAppBarContent(
                title = stringResource(titleRes),
                showBackButton = backStack is HomeNavigation.SeeAllRoute
            ) {
                navigator.navigateBack()
            }
        }
    ) { paddingValues ->
        GridContainer(
            modifier = Modifier.padding(paddingValues),
            loadNextItems = {
                viewModel.loadNextMovies()
            }
        ) {
            items(items = movieList.value, contentType = { "media" }) { movie ->
                MediaCard(
                    title = movie.title,
                    posterPath = movie.posterPath,
                ) {
                    navigator.navigateTo(
                        route = HomeNavigation.ContentDetailRoute(
                            id = movie.id,
                            mediaCategory = movie.type
                        )
                    )
                }
            }
        }
    }
}