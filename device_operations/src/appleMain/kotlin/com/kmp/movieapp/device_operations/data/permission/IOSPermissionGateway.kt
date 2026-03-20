package com.kmp.movieapp.device_operations.data.permission

import kotlinx.coroutines.flow.Flow

/**
 * Exposes iOS runtime permission requests required by device operations.
 */
internal interface IOSPermissionGateway {

    /**
     * Requests camera permission.
     */
    fun requestCameraPermission(): Flow<PermissionState>

    /**
     * Requests location permission.
     */
    fun requestLocationPermission(): Flow<PermissionState>
}