package com.kmp.movieapp.core.ui.imageloader

import androidx.compose.ui.graphics.ImageBitmap

actual suspend fun loadImageBitmapFromUri(uri: String): ImageBitmap? {
    return ImageBitmap(0, 0)
}