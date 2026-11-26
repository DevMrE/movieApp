package com.kmp.movieapp.search.presentation.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.search.presentation.SearchAction
import com.kmp.movieapp.search.presentation.SearchTextField
import com.kmp.movieapp.search.presentation.SearchViewModel
import com.kmp.movieapp.search.presentation.component.FilterGenreComponent
import com.kmp.movieapp.search.presentation.model.UiSearch
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.search_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen() {
    val viewModel = koinViewModel<SearchViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreenContent(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Composable
private fun SearchScreenContent(
    uiState: UiSearch,
    onAction: (SearchAction) -> Unit
) {
    val focusManager = LocalFocusManager.current
    stringResource(Res.string.search_title)


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.padding.ten)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    focusManager.clearFocus()
                },
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.twentyFive,
            alignment = Alignment.Top
        )
    ) {
        item {
            SearchTextField(
                query = uiState.search ?: "",
                onQueryChanged = {
                    onAction(SearchAction.OnSearchUpdated(it))
                },
                modifier = Modifier.padding(top = MaterialTheme.padding.thirtySix)
            )
        }

        item {
            FilterGenreComponent(
                modifier = Modifier,
                genres = uiState.genreFilter?.genres
            ) { genre ->
                onAction(SearchAction.OnGenreUpdated(genre))
                focusManager.clearFocus()
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun SearchScreenPreview() {
    AppTheme {
        SearchScreenContent(
            uiState = UiSearch(
                search = "Hi"
            ),
            onAction = {}
        )
    }
}