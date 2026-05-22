package com.kmp.movieapp.core.util.navigation.route

import com.kmp.movieapp.core.util.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavigation: Route {

    @Serializable
    data object Home : AppNavigation

    @Serializable
    data object Browse : AppNavigation

    @Serializable
    data object More : AppNavigation

    @Serializable
    data object Search: AppNavigation
}
