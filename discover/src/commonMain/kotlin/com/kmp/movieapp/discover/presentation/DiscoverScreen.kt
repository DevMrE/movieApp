package com.kmp.movieapp.discover.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.discover.presentation.component.FilterComponent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DiscoverScreen() {

    val viewModel = koinViewModel<DiscoverViewModel>()
    val discover by viewModel.discoverState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.ten,
            alignment = Alignment.CenterVertically
        )
    ) {
        FilterComponent(
            filters = discover?.filter,
            onFilterClicked = { uiFilter ->
                viewModel.onAction(DiscoverAction.OnFilterClicked(uiFilter))
            }
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(MaterialTheme.size.defaultCardWidth)
        ) {
            items(discover?.contentList ?: return@LazyVerticalGrid) { content ->
                MediaCard(
                    title = content.title,
                    posterPath = content.posterPath,
                    onClick = {
                        viewModel.onAction(DiscoverAction.OnContentClicked(uiMediaCard = content))
                    }
                )
            }
        }
    }
}