package com.kmp.movieapp.discover.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.ui.material.padding

@Composable
internal fun DiscoverHorizontalListComponent(
    items: List<UiMediaCard>,
    bigCard: Boolean = false,
    onItemClick: (UiMediaCard) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.twelfth),
        contentPadding = PaddingValues(all = MaterialTheme.padding.ten)
    ) {
        items(
            items = items,
        ) { item ->
            MediaCard(
                title = item.title,
                posterPath = if (bigCard) item.backdropPath else item.posterPath,
                bigCard = bigCard,
            ) { onItemClick(item) }
        }
    }
}