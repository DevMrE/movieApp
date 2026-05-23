package com.kmp.movieapp.discover.presentation.destination

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data class ContentDetailDestination(
    val id: String,
    val type: MediaCategory
) : Route