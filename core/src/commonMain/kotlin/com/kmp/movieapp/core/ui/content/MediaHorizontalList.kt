package com.kmp.movieapp.core.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.ui.material.padding

@Composable
fun MediaHorizontalList(
    items: List<UiMediaCard>,
    bigCard: Boolean = false,
    onItemClick: (UiMediaCard) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.thirty),
        contentPadding = PaddingValues(horizontal = MaterialTheme.padding.thirty)
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