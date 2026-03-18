package com.kmp.movieapp.core.permission.domain

import com.kmp.movieapp.core.permission.AndroidPermissionLauncher
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
internal class AndroidPermissionsController(
    private val androidLocationProvider: AndroidLocationProvider,
    private val androidGalleryProvider: AndroidGalleryProvider
) : PermissionsController {

    private var androidPermissionLauncher: AndroidPermissionLauncher? = null

    /**
     * Binds the concrete Activity-bound launcher to this controller.
     */
    fun bindLauncher(androidPermissionLauncher: AndroidPermissionLauncher) {
        this.androidPermissionLauncher = androidPermissionLauncher
    }

    override fun camera(): Flow<PermissionResult<Unit>> {
        val launcher = androidPermissionLauncher
            ?: return flowOf(PermissionResult(PermissionStatus.DENIED))

        return launcher.requestCamera()
            .flatMapLatest { result ->
                when (result) {
                    AndroidPermissionState.GRANTED ->
                        flowOf(PermissionResult(PermissionStatus.GRANTED))

                    AndroidPermissionState.FINAL_DENIED ->
                        flowOf(PermissionResult(PermissionStatus.DENIED))

                    AndroidPermissionState.RETRYABLE_DENIED ->
                        emptyFlow()
                }
            }
    }

    override fun gallery(): Flow<PermissionResult<List<Media>>> {
        return androidGalleryProvider.openGallery()
    }

    override fun location(): Flow<PermissionResult<Location>> {
        val launcher = androidPermissionLauncher
            ?: return flowOf(PermissionResult(PermissionStatus.DENIED))

        return launcher.requestLocation()
            .flatMapLatest { result ->
                when (result) {
                    AndroidPermissionState.GRANTED ->
                        androidLocationProvider.getLocation()

                    AndroidPermissionState.FINAL_DENIED ->
                        flowOf(PermissionResult(PermissionStatus.DENIED))

                    AndroidPermissionState.RETRYABLE_DENIED ->
                        emptyFlow()
                }
            }
    }

    override fun microphone(): Flow<PermissionResult<Unit>> {
        val launcher = androidPermissionLauncher
            ?: return flowOf(PermissionResult(PermissionStatus.DENIED))

        return launcher.requestMicrophone()
            .flatMapLatest { result ->
                when (result) {
                    AndroidPermissionState.GRANTED ->
                        flowOf(PermissionResult(PermissionStatus.GRANTED))

                    AndroidPermissionState.FINAL_DENIED ->
                        flowOf(PermissionResult(PermissionStatus.DENIED))

                    AndroidPermissionState.RETRYABLE_DENIED ->
                        emptyFlow()
                }
            }
    }
}