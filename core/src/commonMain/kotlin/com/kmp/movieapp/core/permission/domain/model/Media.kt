package com.kmp.movieapp.core.permission.domain.model

/**
 * Represents a media item returned by a picker or gallery flow.
 *
 * [uri] is the platform-specific identifier or URI string used to access the media.
 */
data class Media(
    val uri: String,
    val name: String? = null,
    val size: Long? = null
)
