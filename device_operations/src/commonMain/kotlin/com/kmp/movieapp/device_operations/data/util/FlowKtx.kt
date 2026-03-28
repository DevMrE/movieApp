@file:OptIn(ExperimentalCoroutinesApi::class)

package com.kmp.movieapp.device_operations.data.util

import com.kmp.movieapp.device_operations.data.permission.PermissionState
import com.kmp.movieapp.device_operations.domain.result.OperationResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Continues with the given [provider] only when permission is granted.
 *
 * Behavior:
 * - GRANTED -> emits provider results as [OperationResult.Success]
 * - FINAL_DENIED -> emits [OperationResult.Denied]
 * - RETRYABLE_DENIED -> emits nothing
 */
internal fun <T> Flow<PermissionState>.executeAfterPermission(
    provider: () -> Flow<T>
): Flow<OperationResult<T>> = flow {
    collect { state ->
        when (state) {
            PermissionState.GRANTED -> {
                emitAll(
                    provider().map { data ->
                        OperationResult.Success(data)
                    }
                )
            }

            PermissionState.FINAL_DENIED -> {
                emit(OperationResult.Denied)
            }

            PermissionState.RETRYABLE_DENIED -> {
                emitAll(emptyFlow())
            }
        }
    }
}

/**
 * Creates a one-shot callback-based Flow.
 *
 * The flow closes automatically after the first emitted value.
 * Use this for single-result operations such as permission requests,
 * camera capture, image picking, or one-time location retrieval.
 */
internal inline fun <T> createOneShotFlow(
    crossinline block: (send: (T) -> Unit) -> Unit,
    noinline onClose: () -> Unit = {}
): Flow<T> = callbackFlow {

    val send: (T) -> Unit = { value ->
        trySend(value).isSuccess
        close()
    }

    block(send)

    awaitClose(onClose)
}

/**
 * Creates a callback-based Flow that supports multiple emissions.
 *
 * The flow remains active until [onClose] is called or the collector is cancelled.
 * Use this for streaming operations such as continuous location updates,
 * bluetooth scans, or recording state updates.
 */
internal inline fun <T> createCallbackFlow(
    crossinline block: (send: (T) -> Unit, close: () -> Unit) -> Unit,
    noinline onClose: () -> Unit = {}
): Flow<T> = callbackFlow {

    val send: (T) -> Unit = { value ->
        trySend(value).isSuccess
    }

    val closeFlow: () -> Unit = {
        close()
    }

    block(send, closeFlow)

    awaitClose(onClose)
}
