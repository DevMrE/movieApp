package com.kmp.movieapp.overview_list.presentation

import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.ui.container.GridContainer
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.Route
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import org.koin.compose.koinInject
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
    val navigator = koinInject<Navigator<Route>>()

    GridContainer(
        loadNextItems = {
            viewModel.loadNextMovies()
        },
        content = {
            items(items = movieList.value, contentType = { "media" }) { movie ->
                MediaCard(
                    title = movie.title,
                    posterPath = movie.posterPath,
                ) {
                    navigator.navigateTo(
                        route = HomeNavigation.ContentDetail(
                            id = movie.id,
                            mediaCategory = movie.type
                        )
                    )
                }
            }
        }
    )
}