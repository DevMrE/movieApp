package com.kmp.movieapp.core.util.focus_requester

import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.focus.FocusRequester

suspend fun FocusRequester.requestFocusWithRetry(maxRetries: Int = 3) {
    withFrameNanos { }
    repeat(maxRetries) {
        try {
            requestFocus()
            return
        } catch (e: IllegalStateException) {
            withFrameNanos { }
        }
    }
}