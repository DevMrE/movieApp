package com.kmp.movieapp.search.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.navigation.MediaDetailDestination
import com.kmp.navigation.compose.rememberNavigation
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchContent() {
    val searchViewModel = koinViewModel<SearchViewModel>()
    val results by searchViewModel.searchQueryState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    val navigation = rememberNavigation()
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        state = gridState,
        contentPadding = PaddingValues(all = MaterialTheme.padding.five),
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.defaultContentPadding,
            alignment = Alignment.Top
        ),
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.defaultContentPadding,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        items(items = results.searchResults, key = { it.hashCode() }) { movie ->
            MediaCard(
                title = "",
                posterPath = movie.posterPath,
                enableGradient = false
            ) {
                navigation.navigateTo(
                    destination = MediaDetailDestination(
                        id = movie.id,
                        mediaCategory = movie.mediaCategory
                    )
                )
                focusManager.clearFocus(force = true)
            }
        }
    }
}