package com.kmp.movieapp.discover.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.components.mediacard.MediaCardComponent
import com.kmp.movieapp.core.util.mediaCategory.MediaCategory
import com.kmp.movieapp.core.ui.theme.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.model.UiMediaCard

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
            MediaCardComponent(
                title = item.title,
                voteAverage = item.voteAverage,
                posterPath = if (bigCard) item.backdropPath else item.posterPath,
                bigCard = bigCard,
            ) { onItemClick(item) }
        }
    }
}

@PreviewLightDark
@Composable
private fun DiscoverHorizontalListPreview() {
    val list = (0..100).map {
        UiMediaCard(
            id = "",
            voteAverage = 8.0,
            title = "Movie: $it",
            posterPath = null,
            backdropPath = null,
            type = MediaCategory.UNKNOWN
        )
    }

    AppTheme {
        DiscoverHorizontalListComponent(
            items = list,
            bigCard = false,
            onItemClick = {}
        )
    }
}