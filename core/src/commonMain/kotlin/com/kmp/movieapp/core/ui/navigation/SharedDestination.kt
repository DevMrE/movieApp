package com.kmp.movieapp.core.ui.navigation

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data class MediaDetailDestination(
    val id: String,
    val mediaCategory: MediaCategory?
) : Route