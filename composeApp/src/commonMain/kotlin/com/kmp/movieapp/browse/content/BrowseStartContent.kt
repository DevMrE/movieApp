package com.kmp.movieapp.browse.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.browse.BrowseAction
import com.kmp.movieapp.browse.BrowseViewModel
import com.kmp.movieapp.browse.component.FilterComponent
import com.kmp.movieapp.core.ui.container.GridContainer
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.material.padding
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BrowseStartContent() {
    val viewModel = koinViewModel<BrowseViewModel>()
    val discover by viewModel.browseState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.ten,
            alignment = Alignment.CenterVertically
        )
    ) {
        FilterComponent(
            filters = discover?.filter,
            onFilterClicked = { uiFilter ->
                viewModel.onAction(BrowseAction.OnFilterClicked(uiFilter))
            }
        )

        GridContainer(
            loadNextItems = {}
        ) {
            items(discover?.contentList ?: return@GridContainer) { content ->
                MediaCard(
                    title = content.title,
                    posterPath = content.posterPath,
                    onClick = {
                        viewModel.onAction(BrowseAction.OnContentClicked(uiMediaCard = content))
                    }
                )
            }
        }
    }
}