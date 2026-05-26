package com.kmp.movieapp.browse.content

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.ui.container.GridContainer
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.content.model.UiMediaCard

@Composable
fun BrowseContentList(
    modifier: Modifier,
    contentList: List<UiMediaCard>?,
    onContentClicked: (UiMediaCard) -> Unit
) {
    GridContainer(
        modifier
    ) {
        items(contentList ?: return@GridContainer) { content ->
            MediaCard(
                title = content.title,
                posterPath = content.posterPath,
                onClick = { onContentClicked(content) }
            )
        }
    }
}