package com.kmp.movieapp.core.permission.domain

/**
 * Internal Android-only permission request state.
 *
 * This type is intentionally kept out of the public app-layer API.
 */
internal enum class AndroidPermissionState {
    GRANTED,
    RETRYABLE_DENIED,
    FINAL_DENIED
}