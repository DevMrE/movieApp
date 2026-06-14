package com.kmp.movieapp.overview

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ExperimentalGridApi
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.components.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.ic_arrow_up
import com.kmp.movieapp.composeApp.popular_movies_title
import com.kmp.movieapp.composeApp.popular_series_title
import com.kmp.movieapp.core.ui.container.ContentResultComponent
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.style.roundFloatingIconButtonStyle
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import com.kmp.movieapp.core.util.navigation.util.koinNavigation
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(
    ExperimentalGridApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class, ExperimentalFoundationStyleApi::class
)
@Composable
fun OverviewListScreen(mediaCategory: MediaCategory) {

    val viewModel = koinViewModel<OverviewListViewModel>(
        key = mediaCategory.name,
        parameters = { parametersOf(mediaCategory) }
    )

    val movieList by viewModel.movieListState.collectAsStateWithLifecycle()
    val navigator = koinNavigation<HomeNavigation>()
    val backStack = navigator.getCurrentBackStack()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val lazyGridState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    val showFab by remember { derivedStateOf { lazyGridState.firstVisibleItemIndex > 0 } }

    val titleRes = when (mediaCategory) {
        MediaCategory.SERIES -> Res.string.popular_series_title
        else -> Res.string.popular_movies_title
    }
    val interactionSource = remember { MutableInteractionSource() }
    val styleState = remember { MutableStyleState(interactionSource) }
    val floatingActionButtonStyle = roundFloatingIconButtonStyle()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarContent(
                title = stringResource(titleRes),
                showBackButton = backStack is HomeNavigation.SeeAllRoute,
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
            items = movieList,
            modifier = Modifier.padding(paddingValues),
            lazyGridState = lazyGridState,
            loadNextItems = {
                viewModel.loadNextMovies()
            },
        ) { movie ->
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