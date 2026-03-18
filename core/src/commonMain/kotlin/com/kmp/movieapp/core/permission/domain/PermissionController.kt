package com.kmp.movieapp.core.permission.domain

import com.kmp.movieapp.core.permission.domain.model.Location
import com.kmp.movieapp.core.permission.domain.model.Media
import com.kmp.movieapp.core.permission.util.PermissionResult
import kotlinx.coroutines.flow.Flow

/**
 * Exposes cold flows for permission-based features.
 *
 * Each function starts its own permission flow only when collected.
 * Every flow is isolated from the others, so requesting one permission
 * does not affect another permission request.
 */
interface PermissionsController {

    /**
     * Requests camera permission and returns the final permission result.
     *
     * The current contract only reports whether the permission is available.
     * It does not open the camera UI by itself.
     */
    fun camera(): Flow<PermissionResult<Unit>>

    /**
     * Requests gallery access and returns the selected media list.
     *
     * An empty list may be returned when the picker completed without data.
     */
    fun gallery(): Flow<PermissionResult<List<Media>>>

    /**
     * Requests location permission and returns the current location if available.
     */
    fun location(): Flow<PermissionResult<Location>>

    /**
     * Requests microphone permission and returns the final permission result.
     */
    fun microphone(): Flow<PermissionResult<Unit>>
}