package com.kmp.movieapp.content_detail.presentation.component.title

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.core.ui.imageloader.ImageLoader
import com.kmp.movieapp.core.ui.material.gradient
import com.kmp.movieapp.core.ui.material.mobileSize
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.boolean.isGreaterThan
import com.kmp.movieapp.core.util.composable.applyIfElse
import com.kmp.movieapp.core.util.composable.gradientOverlay

internal fun LazyListScope.contentImage(
    posterPath: String,
    onBackClicked: () -> Unit,
    isLoading: (Boolean) -> Unit,
) {
    item {
        ContentImage(
            posterPath = posterPath,
            onBackClicked = onBackClicked,
            isLoading = isLoading
        )
    }
}

@Composable
private fun ContentImage(
    posterPath: String,
    onBackClicked: () -> Unit,
    isLoading: (Boolean) -> Unit

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.mobileSize.detailHeaderHeight)
    ) {
        // Background poster
        ImageLoader(
            url = posterPath,
            modifier = Modifier
                .heightIn(MaterialTheme.mobileSize.detailHeaderHeight)
                .applyIfElse(
                    condition = isSystemInDarkTheme(),
                    ifTrue = {
                        gradientOverlay(MaterialTheme.gradient.detailCardDarkMode)
                    },
                    ifFalse = {
                        gradientOverlay(MaterialTheme.gradient.detailCardLightMode)
                    }
                ),
            contentScale = ContentScale.Crop,
            loadingProgress = {
                isLoading(it.isGreaterThan(0.0f))
            }
        )

        BackButton(
            modifier = Modifier.align(alignment = Alignment.TopStart),
            onBackClicked = onBackClicked
        )
    }
}

@Composable
@PreviewLightDark
private fun DetailHeaderPreview() {
    AppTheme {
        Surface {
            ContentImage(
                posterPath = "",
                onBackClicked = {
                }
            ) {
            }
        }
    }
}