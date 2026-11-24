package com.kmp.movieapp.overview.presentation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.MutableStyleState
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.koinNavigation
import com.kmp.movieapp.components.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.components.mediacard.MediaCardComponent
import com.kmp.movieapp.components.mediacard.MovieCardPreviewData
import com.kmp.movieapp.core.ui.container.ContentResultComponent
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.style.roundFloatingIconButtonStyle
import com.kmp.movieapp.core.util.koin.koinComposableViewModel
import com.kmp.movieapp.core.util.navigation.PreviewNavigation
import com.kmp.movieapp.discover.presentation.navigation.DiscoverRoute
import com.kmp.movieapp.model.UiMediaCard
import com.kmp.movieapp.overview.presentation.mapper.UiOverviewStringMapper
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.ic_arrow_up
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.vectorResource
import org.koin.core.parameter.parametersOf

@Composable
fun OverviewListScreen(mediaCategory: MediaCategory) {

    val viewModel = koinComposableViewModel<OverviewListViewModel>(
        key = mediaCategory.name,
        parameters = { parametersOf(mediaCategory) }
    )
    val navigator = koinNavigation<DiscoverRoute>()
    val uiState by viewModel.overviewUiState.collectAsStateWithLifecycle()

    OverviewListContent(
        navigator = navigator,
        mediaCategory = mediaCategory,
        uiState = uiState,
        loadNextItems = viewModel::loadNextItems
    )
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationStyleApi::class,
    ExperimentalMaterial3ExpressiveApi::class
)
@Composable
private fun <T: NavKey> OverviewListContent(
    navigator: Navigator<T>,
    mediaCategory: MediaCategory,
    uiState: OverviewUiState,
    loadNextItems: () -> Unit
) {
    val backStack = navigator.getCurrentBackStack()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val lazyGridState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    val showFab by remember { derivedStateOf { lazyGridState.firstVisibleItemIndex > 0 } }

    val interactionSource = remember { MutableInteractionSource() }
    val styleState = remember { MutableStyleState(interactionSource) }
    val floatingActionButtonStyle = roundFloatingIconButtonStyle()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarContent(
                title = UiOverviewStringMapper.toTitle(mediaCategory),
                showBackButton = backStack is DiscoverRoute.SeeAllRoute,
                scrollBehavior = scrollBehavior
            ) {
                navigator.navigateBack()
            }
        },
        floatingActionButton = {
            IconButton(
                modifier = Modifier
                    .styleable(styleState = styleState, style = floatingActionButtonStyle)
                    .animateFloatingActionButton(
                        visible = showFab,
                        alignment = Alignment.BottomEnd
                    ),
                onClick = {
                    scope.launch {
                        lazyGridState.animateScrollToItem(index = 1, scrollOffset = 0)
                    }
                }
            ) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_arrow_up),
                    contentDescription = null,
                    modifier = Modifier.rotate(2f)
                )
            }
        }
    ) { paddingValues ->
        ContentResultComponent(
            items = uiState.contentList,
            modifier = Modifier.padding(paddingValues),
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
}

@PreviewLightDark
@Composable
private fun OverviewListPreview(
    @PreviewParameter(MovieCardPreviewData::class) contentList: List<UiMediaCard>
) {

    OverviewListContent(
        navigator = PreviewNavigation,
        mediaCategory = MediaCategory.MOVIE,
        uiState = OverviewUiState(
            contentList = contentList
        ),
        loadNextItems = {

        }
    )
}