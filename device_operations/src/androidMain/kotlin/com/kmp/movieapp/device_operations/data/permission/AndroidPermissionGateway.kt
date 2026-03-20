package com.kmp.movieapp.device_operations.data.permission

import kotlinx.coroutines.flow.Flow

/**
 * Exposes Android runtime permission requests required by device operations.
 */
internal interface AndroidPermissionGateway {

    /**
     * Requests camera permission.
     */
    fun requestCameraPermission(): Flow<AndroidPermissionState>

    /**
     * Requests location permission.
     */
    fun requestLocationPermission(): Flow<AndroidPermissionState>
}