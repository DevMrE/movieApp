package com.kmp.movieapp.core.util.permission.util

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

internal suspend fun <T> suspendPermission(
    block: (callback: (T) -> Unit) -> Unit
): T = suspendCancellableCoroutine { continuation ->
    block { result -> continuation.resume(result) }
}