package com.kmp.movieapp.overview.presentation

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.devmre.compose.navigation3.helper.Navigator
import com.kmp.movieapp.components.mediacard.MediaCardComponent
import com.kmp.movieapp.components.mediacard.MovieCardPreviewData
import com.kmp.movieapp.core.ui.container.ContentResultComponent
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.koin.koinComposableViewModel
import com.kmp.movieapp.core.util.navigation.PreviewNavigation
import com.kmp.movieapp.discover.presentation.navigation.DiscoverRoute
import com.kmp.movieapp.model.UiMediaCard
import org.koin.core.parameter.parametersOf

@Composable
fun OverviewListScreen(
    mediaCategory: MediaCategory,
    navigator: Navigator<DiscoverRoute>
) {
    val viewModel = koinComposableViewModel<OverviewListViewModel>(
        key = mediaCategory.name,
        parameters = { parametersOf(mediaCategory) }
    )
    val uiState by viewModel.overviewUiState.collectAsStateWithLifecycle()

    OverviewListContent(
        navigator = navigator,
        uiState = uiState,
        loadNextItems = viewModel::loadNextItems
    )
}

@Composable
private fun <T : NavKey> OverviewListContent(
    navigator: Navigator<T>,
    uiState: OverviewUiState,
    loadNextItems: () -> Unit
) {
    val lazyGridState = rememberLazyGridState()

    ContentResultComponent(
        items = uiState.contentList,
        lazyGridState = lazyGridState,
        loadNextItems = {
            loadNextItems()
        },
    ) { movie ->
        MediaCardComponent(
            title = movie.title,
            voteAverage = movie.voteAverage,
            posterPath = movie.posterPath,
        ) {
            (navigator as Navigator<DiscoverRoute>).let {
                navigator.navigateTo(
                    route = DiscoverRoute.ContentDetailRoute(
                        id = movie.id,
                        mediaCategory = movie.type
                    )
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun OverviewListPreview(
    @PreviewParameter(MovieCardPreviewData::class) contentList: List<UiMediaCard>
) {
    AppTheme {
        OverviewListContent(
            navigator = PreviewNavigation,
            uiState = OverviewUiState(
                contentList = contentList
            )
        ) {

        }
    }
}