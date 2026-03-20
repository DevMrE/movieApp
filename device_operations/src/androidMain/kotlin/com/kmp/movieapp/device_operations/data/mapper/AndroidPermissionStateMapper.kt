package com.kmp.movieapp.device_operations.data.mapper

import com.kmp.movieapp.device_operations.data.permission.AndroidPermissionState
import com.kmp.movieapp.device_operations.data.permission.PermissionState


/**
 * Maps Android-specific permission states to the shared permission state.
 */
internal fun AndroidPermissionState.toPermissionState(): PermissionState {
    return when (this) {
        AndroidPermissionState.GRANTED -> PermissionState.GRANTED
        AndroidPermissionState.RETRYABLE_DENIED -> PermissionState.RETRYABLE_DENIED
        AndroidPermissionState.FINAL_DENIED -> PermissionState.FINAL_DENIED
    }
}