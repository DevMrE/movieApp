package com.kmp.movieapp.core.permission.domain


/**
 * Represents the final permission outcome exposed to the app layer.
 *
 * The API is intentionally simplified:
 * - [GRANTED] means the permission is available and the feature can proceed.
 * - [DENIED] means the permission is not available, regardless of whether the
 *   system may show the permission dialog again or the user must go to settings.
 */
enum class PermissionStatus {
    GRANTED,
    DENIED
}