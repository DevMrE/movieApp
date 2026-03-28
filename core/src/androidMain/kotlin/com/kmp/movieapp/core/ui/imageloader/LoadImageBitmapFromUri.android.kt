package com.kmp.movieapp.core.ui.imageloader

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.context.GlobalContext

actual suspend fun loadImageBitmapFromUri(uri: String): ImageBitmap? = withContext(Dispatchers.IO) {
    runCatching {
        val context = GlobalContext.get().get<android.content.Context>()
        context.contentResolver.openInputStream(uri.toUri())?.use { inputStream ->
            BitmapFactory.decodeStream(inputStream)?.asImageBitmap()
        }
    }.getOrNull()
}