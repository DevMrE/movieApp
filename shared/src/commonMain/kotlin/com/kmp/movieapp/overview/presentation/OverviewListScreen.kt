package com.kmp.movieapp.overview.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.devmre.compose.navigation3.helper.Navigator
import com.kmp.movieapp.components.mediacard.MediaCardComponent
import com.kmp.movieapp.components.mediacard.MovieCardPreviewData
import com.kmp.movieapp.components.topbar.component.TopAppBarComponent
import com.kmp.movieapp.components.topbar.util.rememberTopBarConfig
import com.kmp.movieapp.core.ui.container.ContentResultComponent
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.koin.koinComposableViewModel
import com.kmp.movieapp.core.util.navigation.PreviewNavigation
import com.kmp.movieapp.discover.presentation.navigation.DiscoverRoute
import com.kmp.movieapp.model.UiMediaCard
import org.jetbrains.compose.resources.stringResource
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
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

    val topBarConfig = rememberTopBarConfig<DiscoverRoute>()

    Scaffold(
        topBar = {
            topBarConfig.title?.let {
                TopAppBarComponent(
                    title = stringResource(it),
                    onBackClick = topBarConfig.onBackClick
                )
            }
        }
    ) { paddingValues ->

        OverviewListContent(
            navigator = navigator,
            uiState = uiState,
            modifier = Modifier.padding(paddingValues),
            loadNextItems = viewModel::loadNextItems
        )
    }
}

@Composable
private fun <T : NavKey> OverviewListContent(
    navigator: Navigator<T>,
    uiState: OverviewUiState,
    modifier: Modifier = Modifier,
    loadNextItems: () -> Unit
) {
    val lazyGridState = rememberLazyGridState()

    ContentResultComponent(
        items = uiState.contentList,
        lazyGridState = lazyGridState,
        modifier = modifier,
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