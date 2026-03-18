package com.kmp.movieapp.core.permission.util

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Creates a one-shot permission flow and closes it automatically after the first result.
 *
 * This helper is useful for bridging platform callbacks into a cold Flow while
 * keeping the implementation small and consistent across permission handlers.
 */
inline fun <T> createPermissionFlow(
    noinline awaitClose: (() -> Unit)? = null,
    crossinline block: (send: (T) -> Unit) -> Unit,
): Flow<T> = callbackFlow {

    val send: (T) -> Unit = { result ->
        trySend(result)
        close()
    }

    block(send)

    awaitClose {
        awaitClose?.invoke()
    }
}