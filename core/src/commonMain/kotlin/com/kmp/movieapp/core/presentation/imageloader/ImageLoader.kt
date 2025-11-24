package com.kmp.movieapp.core.presentation.imageloader

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ImageLoader(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    loadingContent: (@Composable () -> Unit)? = null,
) {

    KamelImage(
        resource = { asyncPainterResource(url) },
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale,
        onLoading = { progress ->
            loadingContent?.let {
                it()
            } ?: CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        },
        onFailure = { exception ->
            // Optional: Fallback, Error-Icon, Logging, ...
            Text(
                text = "Fehler beim Laden",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    )
}