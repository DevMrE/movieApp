package com.kmp.movieapp.core.util.navigation.route

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeNavigation : Route {

    @Serializable
    data object InitialScreenRoute : HomeNavigation

    @Serializable
    data class SeeAllRoute(val mediaCategory: MediaCategory) : HomeNavigation

    @Serializable
    data class ContentDetailRoute(val id: String, val mediaCategory: MediaCategory) : HomeNavigation
}