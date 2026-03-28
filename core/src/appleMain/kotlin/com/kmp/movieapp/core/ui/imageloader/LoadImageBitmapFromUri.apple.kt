package com.kmp.movieapp.core.ui.imageloader

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image
import platform.Foundation.NSData
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfURL
import platform.posix.memcpy

actual suspend fun loadImageBitmapFromUri(uri: String): ImageBitmap? =
    withContext(Dispatchers.Default) {
        runCatching {
            val nsUrl = NSURL.URLWithString(uri) ?: return@runCatching null
            val data = NSData.dataWithContentsOfURL(nsUrl) ?: return@runCatching null

            Image.makeFromEncoded(data.toByteArray()).toComposeImageBitmap()
        }.getOrNull()
    }

@OptIn(ExperimentalForeignApi::class)
private fun NSData.toByteArray(): ByteArray {
    val size = length.toInt()
    val byteArray = ByteArray(size)

    byteArray.usePinned { pinned ->
        memcpy(pinned.addressOf(0), bytes, length)
    }

    return byteArray
}