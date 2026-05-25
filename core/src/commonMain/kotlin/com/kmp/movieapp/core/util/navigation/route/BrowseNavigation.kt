package com.kmp.movieapp.core.util.navigation.route

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
sealed interface BrowseNavigation : Route {

    @Serializable
    data object InitialScreenRoute : BrowseNavigation

    @Serializable
    data class ContentDetailRoute(val id: String, val mediaCategory: MediaCategory) :
        BrowseNavigation
}