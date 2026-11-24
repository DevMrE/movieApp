package com.kmp.movieapp.search.presentation.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kmp.movieapp.components.mediacard.MediaCardComponent
import com.kmp.movieapp.core.ui.container.ContentResultComponent
import com.kmp.movieapp.model.UiMediaCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContentList(
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
        MediaCardComponent(
            title = content.title,
            voteAverage = content.voteAverage,
            posterPath = content.posterPath,
            onClick = { onContentClicked(content) }
        )
    }
}