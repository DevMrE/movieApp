package com.kmp.movieapp.core.util.navigation.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavigation : NavKey {

    @Serializable
    data object HomeRoute : AppNavigation

    @Serializable
    data object BrowseRoute : AppNavigation

    @Serializable
    data object MoreRoute : AppNavigation

}
