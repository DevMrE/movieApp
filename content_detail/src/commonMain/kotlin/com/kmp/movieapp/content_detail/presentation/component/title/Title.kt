package com.kmp.movieapp.content_detail.presentation.component.title

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.core.ui.imageloader.ImageLoader
import com.kmp.movieapp.core.ui.material.gradient
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.boolean.isGreaterThan
import com.kmp.movieapp.core.util.composable.applyIfElse
import com.kmp.movieapp.core.util.composable.gradientOverlay

internal fun LazyListScope.title(
    title: String,
    mediaInfo: AnnotatedString?,
    posterPath: String,
    onBackClicked: () -> Unit,
    isLoading: (Boolean) -> Unit,
) {
    item {
        TitleContent(
            title = title,
            mediaInfo = mediaInfo,
            posterPath = posterPath,
            onBackClicked = onBackClicked,
            isLoading = isLoading
        )
    }
}

@Composable
private fun TitleContent(
    title: String,
    mediaInfo: AnnotatedString?,
    posterPath: String,
    onBackClicked: () -> Unit,
    isLoading: (Boolean) -> Unit,

) {
    Box {
        // Background poster
        ImageLoader(
            url = posterPath,
            modifier = Modifier
                .applyIfElse(
                    condition = isSystemInDarkTheme(),
                    ifTrue = {
                        gradientOverlay(MaterialTheme.gradient.detailCardDarkMode)
                    },
                    ifFalse = {
                        gradientOverlay(MaterialTheme.gradient.detailCardLightMode)
                    }
                ),
            contentScale = ContentScale.FillWidth,
            loadingProgress = {
                isLoading(it.isGreaterThan(0.0f))
            }
        )

        BackButton(
            modifier = Modifier.align(alignment = Alignment.TopStart),
            onBackClicked = onBackClicked
        )

        MediaTitle(
            modifier = Modifier.align(Alignment.BottomCenter),
            title = title,
            mediaInfo = mediaInfo,
        )
    }
}

@Composable
@PreviewLightDark
private fun DetailHeaderPreview() {
    AppTheme {
        Surface {
            TitleContent(
                title = "Movie",
                mediaInfo = buildAnnotatedString {
                    append("1997 \u2022 186")
                },
                posterPath = "",
                onBackClicked = {
                }
            ) {
            }
        }
    }
}