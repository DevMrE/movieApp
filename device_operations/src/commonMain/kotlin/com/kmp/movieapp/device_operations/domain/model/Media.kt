package com.kmp.movieapp.device_operations.domain.model

/**
 * Represents a media file (image, video, etc.).
 *
 * @param uri String representation of the media location.
 * @param name Optional display name.
 * @param mimeType Optional MIME type of the file.
 */
data class Media(
    val uri: String,
    val name: String? = null,
    val mimeType: String? = null
)