package com.kmp.movieapp.browse.presentation.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kmp.movieapp.core.ui.container.ContentResultComponent
import com.kmp.movieapp.core.ui.content.MediaCard
import com.kmp.movieapp.core.ui.content.model.UiMediaCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseContentList(
    contentList: List<UiMediaCard>?,
    onLoadNextPage: () -> Unit,
    onContentClicked: (UiMediaCard) -> Unit,
) {
    ContentResultComponent(
        items = contentList,
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .background(Color.Transparent),
        loadNextItems = onLoadNextPage
    ) { content ->
        MediaCard(
            title = content.title,
            posterPath = content.posterPath,
            onClick = { onContentClicked(content) }
        )
    }
}