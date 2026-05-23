package com.kmp.movieapp.discover.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.ui.container.GridContainer
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.discover.presentation.component.FilterComponent
import com.kmp.movieapp.discover.presentation.model.UiDiscover

@Composable
fun DiscoverComponent(
    discover: UiDiscover?,
    onAction: (DiscoverAction) -> Unit
) {

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
                onAction(DiscoverAction.OnFilterClicked(uiFilter))
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
                        onAction(DiscoverAction.OnContentClicked(uiMediaCard = content))
                    }
                )
            }
        }
    }
}