package com.kmp.movieapp.core.permission.util

import com.kmp.movieapp.core.permission.domain.PermissionStatus

/**
 * Represents the outcome of a permission-driven operation.
 *
 * [status] describes whether the permission flow finished successfully.
 * [data] contains the optional payload returned by the feature after the
 * permission was granted.
 */
class PermissionResult<T>(
    val status: PermissionStatus,
    val data: T? = null
) {

    /**
     * Executes [block] only when the permission flow succeeded.
     *
     * The payload can be null depending on the feature contract.
     */
    inline fun onGranted(block: (T?) -> Unit): PermissionResult<T> {
        if (status == PermissionStatus.GRANTED) {
            block(data)
        }
        return this
    }

    /**
     * Executes [block] only when the permission can no longer be meaningfully
     * requested through the system dialog and the app should offer a settings path.
     */
    inline fun onDenied(block: () -> Unit): PermissionResult<T> {
        if (status == PermissionStatus.DENIED) {
            block()
        }
        return this
    }
}