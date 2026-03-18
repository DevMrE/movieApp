package com.kmp.movieapp.core.permission.domain

import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.permission.domain.model.Location
import com.kmp.movieapp.core.permission.domain.model.Media
import com.kmp.movieapp.core.permission.util.PermissionResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

/**
 * Android implementation of [PermissionsController].
 *
 * This controller orchestrates permission requests and delegates feature-specific
 * work to dedicated providers. It intentionally keeps business decisions small
 * and avoids deep callback nesting.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AndroidPermissionsController(
    private val androidLocationProvider: AndroidLocationProvider,
    private val androidGalleryProvider: AndroidGalleryProvider
) : PermissionsController {

    private var androidPermissionLauncher: AndroidPermissionLauncher? = null

    fun bindLauncher(launcher: AndroidPermissionLauncher) {
        androidPermissionLauncher = launcher
    }

    /**
     * Requests camera permission and returns the final result.
     */
    override fun camera(): Flow<PermissionResult<Unit>> {
        val launcher = androidPermissionLauncher
            ?: return flowOf(PermissionResult(PermissionStatus.DENIED))

        return launcher.requestCamera()
            .map { granted ->
                Logger.i(tag = "Permission", messageString = "camera granted?: $granted")

                PermissionResult(
                    status = if (granted) PermissionStatus.GRANTED else PermissionStatus.DENIED
                )
            }
    }

    /**
     * Requests location permission and resolves the actual location only after
     * the permission was granted.
     */
    override fun location(): Flow<PermissionResult<Location>> {
        val launcher = androidPermissionLauncher
            ?: return flowOf(PermissionResult(PermissionStatus.DENIED))

        return launcher.requestLocation()
            .flatMapLatest { granted ->
                Logger.i (tag = "Permission", messageString = "location granted?: $granted")
                if (granted) androidLocationProvider.getLocation()
                else flowOf(PermissionResult(PermissionStatus.DENIED))
            }
    }

    /**
     * Requests microphone permission and returns the final result.
     */
    override fun microphone(): Flow<PermissionResult<Unit>> {
        val launcher = androidPermissionLauncher
            ?: return flowOf(PermissionResult(PermissionStatus.DENIED))

        return launcher.requestMicrophone()
            .map { granted ->
                Logger.i(tag = "Permission", messageString = "microphone granted?: $granted")
                PermissionResult(
                    status = if (granted) PermissionStatus.GRANTED else PermissionStatus.DENIED
                )
            }
    }

    /**
     * Opens the gallery picker and returns the selected media list.
     *
     * This implementation assumes the picker can be opened directly.
     */
    override fun gallery(): Flow<PermissionResult<List<Media>>> {
        return androidGalleryProvider.openGallery()
    }
}