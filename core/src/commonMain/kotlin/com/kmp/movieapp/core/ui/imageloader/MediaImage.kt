package com.kmp.movieapp.core.ui.imageloader

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

/**
 * Displays a picked or captured media image from its local uri.
 */
@Composable
fun MediaImage(
    imageString: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val imageBitmap by produceState<ImageBitmap?>(
        initialValue = null,
        key1 = imageString
    ) {
        value = loadImageBitmapFromUri(imageString)
    }

    imageBitmap?.let {
        Image(
            bitmap = it,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    } ?: Box(
        modifier = modifier.size(64.dp)
    )
}