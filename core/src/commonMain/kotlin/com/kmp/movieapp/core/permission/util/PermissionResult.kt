package com.kmp.movieapp.core.permission.util

import com.kmp.movieapp.core.permission.domain.PermissionStatus

/**
 * Represents the outcome of a permission-driven operation.
 *
 * [status] describes whether the permission flow ended successfully.
 * [data] contains the optional payload returned by the feature after the
 * permission was granted, such as location data or selected media.
 */
class PermissionResult<T>(
    val status: PermissionStatus,
    val data: T? = null
) {

    /**
     * Executes the given [block] only when the permission flow succeeded.
     *
     * The payload may be null depending on the feature contract.
     */
    inline fun onGranted(block: (T?) -> Unit): PermissionResult<T> {
        if (status == PermissionStatus.GRANTED) {
            block(data)
        }
        return this
    }

    /**
     * Executes the given [block] when the permission flow did not succeed.
     *
     * This includes both normal denial and cases where the user must be
     * redirected to system settings.
     */
    inline fun onDenied(block: () -> Unit): PermissionResult<T> {
        if (status == PermissionStatus.DENIED) {
            block()
        }
        return this
    }
}