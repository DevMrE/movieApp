package com.kmp.movieapp.device_operations.data.controller

import com.kmp.movieapp.device_operations.data.permission.PermissionState
import com.kmp.movieapp.device_operations.data.util.executeAfterPermission
import com.kmp.movieapp.device_operations.domain.controller.DeviceOperationsController
import com.kmp.movieapp.device_operations.domain.model.Location
import com.kmp.movieapp.device_operations.domain.model.Media
import com.kmp.movieapp.device_operations.domain.provider.CameraProvider
import com.kmp.movieapp.device_operations.domain.provider.GalleryProvider
import com.kmp.movieapp.device_operations.domain.provider.LocationProvider
import com.kmp.movieapp.device_operations.domain.result.OperationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Default implementation of [DeviceOperationsController].
 *
 * This class coordinates permission handling and delegates the actual work
 * to the corresponding provider.
 */
internal class DeviceOperationsControllerImpl(
    private val cameraPermission: () -> Flow<PermissionState>,
    private val locationPermission: () -> Flow<PermissionState>,
    private val cameraProvider: CameraProvider,
    private val galleryProvider: GalleryProvider,
    private val locationProvider: LocationProvider
) : DeviceOperationsController {

    /**
     * Requests camera access and continues with photo capture if granted.
     */
    override fun capturePhoto(): Flow<OperationResult<Media>> {
        return cameraPermission()
            .executeAfterPermission {
                cameraProvider.capturePhoto()
            }
    }

    /**
     * Requests gallery access if needed and opens the image picker.
     */
    override fun pickImages(): Flow<OperationResult<List<Media>>> {
        return galleryProvider.pickImages().map { OperationResult.Success(it) }
    }

    /**
     * Requests location access and resolves the current location if granted once.
     */
    override fun getCurrentLocation(): Flow<OperationResult<Location>> {
        return locationPermission()
            .executeAfterPermission {
                locationProvider.getCurrentLocation()
            }
    }

    /**
     * Requests location access and resolves the current location if granted and listen
     * for updates.
     */
    override fun getLocationUpdates(): Flow<OperationResult<Location>> {
        return locationPermission()
            .executeAfterPermission {
                locationProvider.getLocationUpdates()
            }
    }
}