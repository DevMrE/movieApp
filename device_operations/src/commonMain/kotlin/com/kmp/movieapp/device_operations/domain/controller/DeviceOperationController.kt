package com.kmp.movieapp.device_operations.domain.controller

import com.kmp.movieapp.device_operations.domain.model.Location
import com.kmp.movieapp.device_operations.domain.model.Media
import com.kmp.movieapp.device_operations.domain.result.OperationResult
import kotlinx.coroutines.flow.Flow

/**
 * Entry point for device-related operations.
 *
 * Provides high-level APIs for interacting with system features such as
 * camera, gallery, and location.
 */
interface DeviceOperationsController {

    /**
     * Opens the camera and returns the captured photo.
     *
     * @return Flow emitting the operation result.
     */
    fun capturePhoto(): Flow<OperationResult<Media>>

    /**
     * Opens the system image picker and returns selected images.
     *
     * @return Flow emitting the operation result.
     */
    fun pickImages(): Flow<OperationResult<List<Media>>>

    /**
     * Requests the current device location once.
     *
     * @return Flow emitting the operation result.
     */
    fun getCurrentLocation(): Flow<OperationResult<Location>>

    /**
     * Requests the current device location with location updates.
     *
     * @return Flow emitting the operation result.
     */
    fun getLocationUpdates(): Flow<OperationResult<Location>>


//    in future
//    fun getBluetoothDevices(): Flow<OperationResult<Device>>
//    fun getBluetoothUpdates(): Flow<OperationResult<Data>>

}
