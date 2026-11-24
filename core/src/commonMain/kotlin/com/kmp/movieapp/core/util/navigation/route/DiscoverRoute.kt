package com.kmp.movieapp.core.util.navigation.route

import androidx.navigation3.runtime.NavKey
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import kotlinx.serialization.Serializable

@Serializable
sealed interface DiscoverRoute : NavKey {

    @Serializable
    data object InitialScreenRoute : DiscoverRoute

    @Serializable
    data class SeeAllRoute(val mediaCategory: MediaCategory) : DiscoverRoute

    @Serializable
    data class ContentDetailRoute(val id: String, val mediaCategory: MediaCategory) : DiscoverRoute
}