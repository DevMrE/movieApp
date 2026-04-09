package com.kmp.movieapp.content_detail.presentation.component.title

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.content_detail.presentation.action.DetailAction
import com.kmp.movieapp.core.ui.imageloader.ImageLoader
import com.kmp.movieapp.core.ui.material.gradient
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.composable.applyIfElse
import com.kmp.movieapp.core.util.composable.gradientOverlay

internal fun LazyListScope.title(
    title: String,
    mediaInfo: AnnotatedString?,
    posterPath: String,
    onBackClicked: () -> Unit,
    onDetailAction: (DetailAction) -> Unit
) {
    item {
        TitleContent(
            title = title,
            mediaInfo = mediaInfo,
            posterPath = posterPath,
            onBackClicked = onBackClicked,
            onDetailAction = onDetailAction
        )
    }
}

@Composable
private fun TitleContent(
    title: String,
    mediaInfo: AnnotatedString?,
    posterPath: String,
    onBackClicked: () -> Unit,
    onDetailAction: (DetailAction) -> Unit

) {
    val imageGradient =
        Box {
            // Background poster
            ImageLoader(
                url = posterPath,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.applyIfElse(
                    condition = isSystemInDarkTheme(),
                    ifTrue = {
                        gradientOverlay(MaterialTheme.gradient.detailCardDarkMode)
                    },
                    ifFalse = {
                        gradientOverlay(MaterialTheme.gradient.detailCardLightMode)
                    }
                )
            )

            BackButton(
                modifier = Modifier.align(alignment = Alignment.TopStart),
                onBackClicked = onBackClicked
            )

            MediaTitle(
                modifier = Modifier.align(Alignment.BottomCenter),
                title = title,
                mediaInfo = mediaInfo,
                onDetailAction = onDetailAction
            )
        }
}

@Composable
@PreviewLightDark
private fun DetailHeaderPreview() {
    AppTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            title(
                title = "Movie",
                mediaInfo = buildAnnotatedString {
                    append("1997 \u2022 186")
                },
                posterPath = "",
                onBackClicked = {
                },
                onDetailAction = {
                }
            )
        }
    }
}