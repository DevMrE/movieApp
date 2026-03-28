package com.kmp.movieapp.core.ui.imageloader

import androidx.compose.ui.graphics.ImageBitmap

/**
 * Loads a platform image from the given local uri and converts it to [ImageBitmap].
 */
expect suspend fun loadImageBitmapFromUri(uri: String): ImageBitmap?