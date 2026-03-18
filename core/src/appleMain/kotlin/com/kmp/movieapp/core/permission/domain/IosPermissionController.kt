package com.kmp.movieapp.core.permission.domain

import com.kmp.movieapp.core.permission.domain.model.Location
import com.kmp.movieapp.core.permission.domain.model.Media
import com.kmp.movieapp.core.permission.util.PermissionResult
import kotlinx.coroutines.flow.Flow

/**
 * iOS implementation of [PermissionsController].
 */
class IOSPermissionsController(
    private val locationProvider: IOSLocationProvider
) : PermissionsController {

    override fun camera(): Flow<PermissionResult<Unit>> {
        return IOSPermissionHandler.requestCamera()
    }

    override fun gallery(): Flow<PermissionResult<List<Media>>> {
        return IOSPermissionHandler.requestGallery()
    }

    override fun location(): Flow<PermissionResult<Location>> {
        return locationProvider.getLocation()
    }

    override fun microphone(): Flow<PermissionResult<Unit>> {
        return IOSPermissionHandler.requestMicrophone()
    }
}