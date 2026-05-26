package com.kmp.movieapp.browse.content

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.kmp.movieapp.core.ui.container.GridContainer
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.content.model.UiMediaCard

@Composable
fun BrowseContentList(
    contentList: List<UiMediaCard>?,
    onContentClicked: (UiMediaCard) -> Unit
) {
    GridContainer {
        items(contentList ?: return@GridContainer) { content ->
            MediaCard(
                title = content.title,
                posterPath = content.posterPath,
                onClick = { onContentClicked(content) }
            )
        }
    }
}