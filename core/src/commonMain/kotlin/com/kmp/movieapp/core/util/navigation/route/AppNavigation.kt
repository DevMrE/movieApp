package com.kmp.movieapp.core.util.navigation.route

import com.kmp.movieapp.core.util.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavigation : Route {

    @Serializable
    data object HomeRoute : AppNavigation

    @Serializable
    data object BrowseRoute : AppNavigation

    @Serializable
    data object MoreRoute : AppNavigation

}
