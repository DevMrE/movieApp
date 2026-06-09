package com.kmp.movieapp.browse.presentation.content

import androidx.compose.runtime.Composable
import com.kmp.movieapp.core.ui.container.ContentResultComponent
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.content.model.UiMediaCard

@Composable
fun BrowseContentList(
    contentList: List<UiMediaCard>?,
    onContentClicked: (UiMediaCard) -> Unit
) {
    ContentResultComponent(
        items = contentList
    ) { content ->
        MediaCard(
            title = content.title,
            posterPath = content.posterPath,
            onClick = { onContentClicked(content) }
        )
    }
}