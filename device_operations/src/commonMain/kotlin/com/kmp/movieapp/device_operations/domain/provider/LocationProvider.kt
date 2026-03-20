package com.kmp.movieapp.device_operations.domain.provider

import com.kmp.movieapp.device_operations.domain.model.Location
import kotlinx.coroutines.flow.Flow

/**
 * Provides the current device location.
 */
interface LocationProvider {

    /**
     * Resolves the current location once.
     */
    fun getCurrentLocation(): Flow<Location>

    /**
     * Emits location updates continuously until the flow is cancelled.
     */
    fun getLocationUpdates(): Flow<Location>
}