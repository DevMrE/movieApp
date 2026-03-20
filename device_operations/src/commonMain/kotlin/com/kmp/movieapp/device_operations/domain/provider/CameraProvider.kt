package com.kmp.movieapp.device_operations.domain.provider

import com.kmp.movieapp.device_operations.domain.model.Media
import kotlinx.coroutines.flow.Flow

/**
 * Provides camera capture functionality.
 */
interface CameraProvider {

    /**
     * Opens the camera flow and returns the captured media.
     */
    fun capturePhoto(): Flow<Media>
}