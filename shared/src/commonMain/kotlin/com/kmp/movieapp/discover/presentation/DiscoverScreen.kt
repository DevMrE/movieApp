package com.kmp.movieapp.discover.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.discover.presentation.component.DiscoverSectionComponent
import com.kmp.movieapp.discover.presentation.model.DiscoverSection
import com.kmp.movieapp.discover.presentation.model.DiscoverUiState
import com.kmp.movieapp.model.UiMediaCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun DiscoverScreen() {
    val viewModel: DiscoverScreenViewModel = koinViewModel()
    val uiState by viewModel.movieScreenState.collectAsStateWithLifecycle()

    DiscoverScreenContent(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Composable
private fun DiscoverScreenContent(
    uiState: DiscoverUiState,
    onAction: (DiscoverAction) -> Unit
) {
    DiscoverSectionComponent(
        refresh = uiState.isLoading.isTrue,
        contentList = uiState.contentSections,
        onAction = onAction
    )
}

@PreviewLightDark
@Composable
private fun DiscoverScreenPreview() {
    val uiState = DiscoverUiState(
        isLoading = false,
        contentSections = setOf(
            DiscoverSection.Trending(
                contentList = (0..100).map {
                    UiMediaCard(
                        id = "",
                        title = "Series $it",
                        posterPath = null,
                        backdropPath = null,
                        voteAverage = null,
                        type = MediaCategory.SERIES
                    )
                }
            ),
            DiscoverSection.PopularMovies(
                contentList = (0..100).map {
                    UiMediaCard(
                        id = "",
                        title = "Series $it",
                        posterPath = null,
                        backdropPath = null,
                        voteAverage = 8.6,
                        type = MediaCategory.SERIES
                    )
                }
            ),
            DiscoverSection.PopularSeries(
                contentList = (0..100).map {
                    UiMediaCard(
                        id = "",
                        title = "Series $it",
                        posterPath = null,
                        backdropPath = null,
                        voteAverage = 7.4,
                        type = MediaCategory.SERIES
                    )
                }
            )
        )
    )

    AppTheme {
        DiscoverScreenContent(
            uiState = uiState
        ) {

        }
    }
}
