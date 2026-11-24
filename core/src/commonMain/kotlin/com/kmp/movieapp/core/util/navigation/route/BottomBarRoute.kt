package com.kmp.movieapp.core.util.navigation.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface BottomBarRoute : NavKey {

    @Serializable
    data object DiscoverRoute : BottomBarRoute

    @Serializable
    data object SearchRoute : BottomBarRoute

    @Serializable
    data object MoreRoute : BottomBarRoute

}
