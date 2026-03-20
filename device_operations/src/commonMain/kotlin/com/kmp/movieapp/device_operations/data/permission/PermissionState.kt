package com.kmp.movieapp.device_operations.data.permission

/**
 * Internal permission state used by platform-specific implementations.
 *
 * This type is not exposed outside the module.
 */
internal enum class PermissionState {
    /**
     * Permission is available and the operation may continue.
     */
    GRANTED,

    /**
     * Permission was denied, but the system may still ask again later.
     */
    RETRYABLE_DENIED,

    /**
     * Permission was denied and the user should be guided to settings.
     */
    FINAL_DENIED
}