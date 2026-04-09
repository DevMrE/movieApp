package com.kmp.movieapp.content_detail.presentation.component.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import com.kmp.movieapp.content_detail.presentation.action.DetailAction
import com.kmp.movieapp.core.ui.imageloader.ImageLoader
import com.kmp.movieapp.core.ui.material.gradient
import com.kmp.movieapp.core.util.composable.gradientOverlay

internal fun LazyListScope.detailHeader(
    title: String,
    mediaInfo: AnnotatedString?,
    posterPath: String,
    onBackClicked: () -> Unit,
    onDetailAction: (DetailAction) -> Unit
) {
    item {
        DetailHeaderContent(
            title = title,
            mediaInfo = mediaInfo,
            posterPath = posterPath,
            onBackClicked = onBackClicked,
            onDetailAction = onDetailAction
        )
    }
}

@Composable
private fun DetailHeaderContent(
    title: String,
    mediaInfo: AnnotatedString?,
    posterPath: String,
    onBackClicked: () -> Unit,
    onDetailAction: (DetailAction) -> Unit

) {

    Box {
        ImageLoader(
            url = posterPath,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.gradientOverlay(MaterialTheme.gradient.detailCard)
        )

        BackButton(
            modifier = Modifier.align(alignment = Alignment.TopStart),
            onBackClicked = onBackClicked
        )

        MediaInfoContent(
            modifier = Modifier.align(Alignment.BottomCenter),
            title = title,
            posterPath = posterPath,
            mediaInfo = mediaInfo,
            onDetailAction = onDetailAction
        )
    }
}
