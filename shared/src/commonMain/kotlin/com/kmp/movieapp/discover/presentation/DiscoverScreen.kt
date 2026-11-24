package com.kmp.movieapp.discover.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.components.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.discover.presentation.component.DiscoverSectionComponent
import com.kmp.movieapp.discover.presentation.model.DiscoverSection
import com.kmp.movieapp.discover.presentation.model.DiscoverUiState
import com.kmp.movieapp.model.UiMediaCard
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.app_name
import org.jetbrains.compose.resources.stringResource
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DiscoverScreenContent(
    uiState: DiscoverUiState,
    onAction: (DiscoverAction) -> Unit
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarContent(
                title = stringResource(Res.string.app_name),
                scrollBehavior = topAppBarScrollBehavior
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        val layoutDir = LocalLayoutDirection.current
        val paddingWithoutBottom = PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDir),
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(layoutDir),
            bottom = 0.dp
        )

        DiscoverSectionComponent(
            refresh = uiState.isLoading.isTrue,
            contentList = uiState.contentSections,
            paddingWithoutBottom = paddingWithoutBottom,
            onAction = onAction
        )
    }
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