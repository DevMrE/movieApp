package com.kmp.movieapp.app.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.presentation.material.padding
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchContent() {
    val searchViewModel = koinViewModel<SearchViewModel>()
    val results = searchViewModel.searchQueryState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(MaterialTheme.padding.sixteen)
    ) {
        items(results.value.searchResults) {
            // TODO: Show
            Logger.i("Search", message = { "find movie: $it" })
            Text(it, color = MaterialTheme.colorScheme.onBackground)
        }
    }
}