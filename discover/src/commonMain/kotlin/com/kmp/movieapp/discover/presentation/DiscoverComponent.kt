package com.kmp.movieapp.discover.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .fillMaxSize()
            .padding(MaterialTheme.padding.defaultContentPadding)
            .padding(MaterialTheme.padding.defaultContentPadding),
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.ten),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.ten)
        ) {
            items(discover?.contentList ?: return@LazyVerticalGrid) { content ->
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