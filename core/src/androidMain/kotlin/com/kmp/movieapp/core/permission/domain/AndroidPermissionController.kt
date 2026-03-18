package com.kmp.movieapp.core.permission.domain

import com.kmp.movieapp.core.permission.AndroidPermissionLauncher
import com.kmp.movieapp.core.permission.AndroidPermissionRequestResult
import com.kmp.movieapp.core.permission.domain.model.Location
import com.kmp.movieapp.core.permission.domain.model.Media
import com.kmp.movieapp.core.permission.util.PermissionResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

/**
 * Android implementation of [PermissionsController].
 *
 * Public semantics:
 * - GRANTED means the feature may proceed.
 * - DENIED means the app should guide the user to system settings.
 * - A retryable Android denial emits nothing and simply completes.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AndroidPermissionsController(
    private val androidLocationProvider: AndroidLocationProvider,
    private val androidGalleryProvider: AndroidGalleryProvider
) : PermissionsController {

    private var androidPermissionLauncher: AndroidPermissionLauncher? = null

    /**
     * Binds the concrete Activity-bound launcher to this controller.
     *
     * The binding must happen from the hosting Activity after the launcher was
     * created and registered.
     */
    fun bindLauncher(androidPermissionLauncher: AndroidPermissionLauncher) {
        this.androidPermissionLauncher = androidPermissionLauncher
    }

    /**
     * Requests camera permission.
     *
     * A retryable denial emits nothing.
     * A final denial emits DENIED.
     */
    override fun camera(): Flow<PermissionResult<Unit>> {
        val launcher = androidPermissionLauncher
            ?: return flowOf(PermissionResult(PermissionStatus.DENIED))

        return launcher.requestCamera()
            .flatMapLatest { result ->
                when (result) {
                    AndroidPermissionRequestResult.GRANTED ->
                        flowOf(PermissionResult(PermissionStatus.GRANTED))

                    AndroidPermissionRequestResult.FINAL_DENIED ->
                        flowOf(PermissionResult(PermissionStatus.DENIED))

                    AndroidPermissionRequestResult.RETRYABLE_DENIED ->
                        emptyFlow()
                }
            }
    }

    /**
     * Opens the gallery flow and returns the selected media.
     */
    override fun gallery(): Flow<PermissionResult<List<Media>>> {
        return androidGalleryProvider.openGallery()
    }

    /**
     * Requests location permission and resolves the actual location only after
     * the permission was granted.
     *
     * A retryable denial emits nothing.
     * A final denial emits DENIED.
     */
    override fun location(): Flow<PermissionResult<Location>> {
        val launcher = androidPermissionLauncher
            ?: return flowOf(PermissionResult(PermissionStatus.DENIED))

        return launcher.requestLocation()
            .flatMapLatest { result ->
                when (result) {
                    AndroidPermissionRequestResult.GRANTED ->
                        androidLocationProvider.getLocation()

                    AndroidPermissionRequestResult.FINAL_DENIED ->
                        flowOf(PermissionResult(PermissionStatus.DENIED))

                    AndroidPermissionRequestResult.RETRYABLE_DENIED ->
                        emptyFlow()
                }
            }
    }

    /**
     * Requests microphone permission.
     *
     * A retryable denial emits nothing.
     * A final denial emits DENIED.
     */
    override fun microphone(): Flow<PermissionResult<Unit>> {
        val launcher = androidPermissionLauncher
            ?: return flowOf(PermissionResult(PermissionStatus.DENIED))

        return launcher.requestMicrophone()
            .flatMapLatest { result ->
                when (result) {
                    AndroidPermissionRequestResult.GRANTED ->
                        flowOf(PermissionResult(PermissionStatus.GRANTED))

                    AndroidPermissionRequestResult.FINAL_DENIED ->
                        flowOf(PermissionResult(PermissionStatus.DENIED))

                    AndroidPermissionRequestResult.RETRYABLE_DENIED ->
                        emptyFlow()
                }
            }
    }
}