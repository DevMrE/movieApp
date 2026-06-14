package com.kmp.movieapp.browse.presentation.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.browse.presentation.BrowseAction
import com.kmp.movieapp.browse.presentation.BrowseViewModel
import com.kmp.movieapp.browse.presentation.component.FilterGenreComponent
import com.kmp.movieapp.components.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.browse_media_title
import com.kmp.movieapp.composeApp.ic_search
import com.kmp.movieapp.core.ui.material.padding
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseStartContent() {
    val viewModel = koinViewModel<BrowseViewModel>()
    val discover by viewModel.browseState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val searchBarState = rememberSearchBarState()
    val scope = rememberCoroutineScope()
    val sheetBarState = rememberBottomSheetScaffoldState()
    val focusRequester = remember { FocusRequester() }
    val titleRes = stringResource(Res.string.browse_media_title)

    BottomSheetScaffold(
        scaffoldState = sheetBarState,
        sheetContent = {
            BrowseContentList(
                contentList = discover.contentList,
                onLoadNextPage = {
                    viewModel.onAction(BrowseAction.OnLoadNextPage)
                }
            ) {
                viewModel.onAction(BrowseAction.OnContentClicked(uiMediaCard = it))
            }
        },
        topBar = {
            TopAppBarContent(
                title = titleRes,
                showTitle = false,
                focusRequester = focusRequester,
                searchBarState = searchBarState,
                query = discover.search,
                onQueryChanged = {
                    viewModel.onAction(BrowseAction.OnSearchUpdated(query = it))
                },
                topBarAction = {
                    AnimatedVisibility(
                        visible = searchBarState.currentValue == SearchBarValue.Collapsed
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    searchBarState.animateToExpanded()
                                    sheetBarState.bottomSheetState.expand()
                                    focusRequester.requestFocus()
                                }
                            },
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.background)
                                .padding(end = MaterialTheme.padding.defaultContentPadding),
                        ) {
                            Icon(
                                imageVector = vectorResource(Res.drawable.ic_search),
                                contentDescription = null,
                            )
                        }
                    }
                }
            )
        },
        sheetContainerColor = MaterialTheme.colorScheme.background,
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.padding.twentyFive,
                alignment = Alignment.Top
            )
        ) {
            FilterGenreComponent(
                modifier = Modifier,
                genres = discover.genreFilter?.genres
            ) { genre ->
                viewModel.onAction(BrowseAction.OnGenreUpdated(genre))
                focusManager.clearFocus(force = true)
            }

            Button(
                onClick = {
                    scope.launch {
                        sheetBarState.bottomSheetState.expand()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("show results")
            }
        }
    }
}