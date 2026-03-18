package com.kmp.movieapp.core.permission.domain

/**
 * Internal Android-only permission request outcome.
 *
 * This type is not exposed to the app layer. It exists to distinguish between:
 * - a granted permission,
 * - a retryable denial where Android may still show the system dialog again,
 * - and a final denial where the app should route the user to settings.
 */
 enum class PermissionRequestResult {
    GRANTED,
    RETRYABLE_DENIED,
    FINAL_DENIED
}