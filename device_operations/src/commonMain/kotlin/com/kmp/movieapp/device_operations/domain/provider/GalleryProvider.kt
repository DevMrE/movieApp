package com.kmp.movieapp.device_operations.domain.provider

import com.kmp.movieapp.device_operations.domain.model.Media
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to image selection from the system picker.
 */
interface GalleryProvider {

    /**
     * Opens the image picker and returns selected media items.
     */
    fun pickImages(): Flow<List<Media>>
}