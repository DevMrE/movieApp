package com.kmp.movieapp.device_operations.domain.result

/**
 * Represents the outcome of a device operation.
 */
sealed class OperationResult<out T> {

    /**
     * Operation completed successfully with data.
     */
    data class Success<T>(val data: T) : OperationResult<T>()

    /**
     * Permission was denied and cannot be requested again.
     * UI should guide the user to system settings.
     */
    data object Denied : OperationResult<Nothing>()

    /**
     * Operation was cancelled by the user (e.g. closing picker or camera).
     */
    data object Cancelled : OperationResult<Nothing>()
}

/**
 * Executes block if operation was successful.
 */
inline fun <T> OperationResult<T>.onGranted(
    block: (T) -> Unit
): OperationResult<T> {
    if (this is OperationResult.Success) {
        block(data)
    }
    return this
}

/**
 * Executes block if permission was denied (final).
 */
inline fun <T> OperationResult<T>.onDenied(
    block: () -> Unit
): OperationResult<T> {
    if (this is OperationResult.Denied) {
        block()
    }
    return this
}

/**
 * Executes block if operation was cancelled by the user.
 */
inline fun <T> OperationResult<T>.onCancelled(
    block: () -> Unit
): OperationResult<T> {
    if (this is OperationResult.Cancelled) {
        block()
    }
    return this
}