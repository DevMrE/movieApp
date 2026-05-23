package com.kmp.movieapp.overview_list.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.Route
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MediaListScreen(mediaCategory: MediaCategory) {

    val viewModel = koinViewModel<OverviewListViewModel>(
        key = mediaCategory.name,
        parameters = { parametersOf(mediaCategory) }
    )

    val movieList = viewModel.movieListState.collectAsStateWithLifecycle()
    val navigator = koinInject<Navigator<Route>>()

    val gridState = rememberLazyGridState()

    val shouldLoadMore by remember {
        derivedStateOf {
            val totalItems = gridState.layoutInfo.totalItemsCount
            val lastVisibleIndex = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleIndex >= totalItems - 6 && totalItems > 0
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            viewModel.loadNextMovies()
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        state = gridState,
        contentPadding = PaddingValues(all = MaterialTheme.padding.five),
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.defaultContentPadding,
            alignment = Alignment.CenterVertically
        ),
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.defaultContentPadding,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        items(items = movieList.value, contentType = { "media" }) { movie ->
            MediaCard(
                title = movie.title,
                posterPath = movie.posterPath,
                height = MaterialTheme.size.defaultCardListHeight
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
}