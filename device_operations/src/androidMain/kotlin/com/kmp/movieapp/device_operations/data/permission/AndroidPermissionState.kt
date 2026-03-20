package com.kmp.movieapp.device_operations.data.permission

/**
 * Represents the Android-specific outcome of a runtime permission request.
 */
internal enum class AndroidPermissionState {
    /**
     * Permission is available and the operation may continue.
     */
    GRANTED,

    /**
     * Permission was denied, but Android may still show the dialog again.
     */
    RETRYABLE_DENIED,

    /**
     * Permission was denied and the user should be guided to settings.
     */
    FINAL_DENIED
}