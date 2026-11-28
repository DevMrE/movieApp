package com.kmp.movieapp.content_detail.presentation.component.title

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.core.ui.imageloader.ImageLoader
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.ui.theme.material.gradient
import com.kmp.movieapp.core.ui.theme.material.size.size
import com.kmp.movieapp.core.util.boolean.isGreaterThan
import com.kmp.movieapp.core.util.composable.applyIfElse
import com.kmp.movieapp.core.util.composable.gradientOverlay

@Composable
internal fun ContentImage(
    posterPath: String,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    isLoading: (Boolean) -> Unit

) {
    // Background poster with automatic color background
    Box(modifier = modifier.fillMaxWidth()) {
        ImageLoader(
            url = posterPath,
            modifier = Modifier
                .height(MaterialTheme.size.imageSize)
                .fillMaxWidth()
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
        ContentImage(
            posterPath = "",
            onBackClicked = {},
            isLoading = {}
        )
    }
}