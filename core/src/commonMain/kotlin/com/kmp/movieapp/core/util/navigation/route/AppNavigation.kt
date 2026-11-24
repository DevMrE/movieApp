package com.kmp.movieapp.core.util.navigation.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavigation : NavKey {

    @Serializable
    data object DiscoverRoute : AppNavigation

    @Serializable
    data object SearchRoute : AppNavigation

    @Serializable
    data object MoreRoute : AppNavigation

}
