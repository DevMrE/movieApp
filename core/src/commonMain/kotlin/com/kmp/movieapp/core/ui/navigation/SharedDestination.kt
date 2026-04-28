package com.kmp.movieapp.core.ui.navigation

import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.navigation.NavDestination
import kotlinx.serialization.Serializable

@Serializable
data class MediaDetailDestination(
    val id: String,
    val contentDetailType: ContentDetailType?
) : NavDestination