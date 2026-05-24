package com.kmp.movieapp.core.ui.imageloader

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.kmp.movieapp.core.Res
import com.kmp.movieapp.core.media_item_not_found
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageLoader(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    loadingProgress: (Float) -> Unit,
    loadingContent: (@Composable () -> Unit)? = null,
) {

    if (url.isNotEmpty()) {
        KamelImage(
            resource = { asyncPainterResource(url) },
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale,
            onLoading = { progress ->
                loadingProgress(progress)
                if (loadingContent == null) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                } else {
                    loadingContent()
                }
            },
            onFailure = { throwable ->
                // Optional: Fallback, Error-Icon, Logging, ...
                Image(
                    painter = painterResource(Res.drawable.media_item_not_found),
                    modifier = modifier,
                    contentDescription = null
                )
            }
        )
    }
}