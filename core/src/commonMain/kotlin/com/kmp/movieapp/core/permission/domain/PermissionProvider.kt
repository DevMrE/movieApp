package com.kmp.movieapp.core.permission.domain

/**
 * Provides functionality that requires a permission.
 */
interface PermissionProvider {

    suspend fun openCamera()

    suspend fun openGallery()

    suspend fun startLocationUpdates()

    suspend fun startMicrophoneRecording()
}