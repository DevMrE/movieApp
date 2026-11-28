package com.kmp.movieapp.search.presentation.navigation

import androidx.navigation3.runtime.NavKey
import com.kmp.movieapp.core.util.mediaCategory.MediaCategory
import kotlinx.serialization.Serializable

@Serializable
sealed interface SearchRoute : NavKey {

    @Serializable
    data object InitialScreenRoute : SearchRoute

    @Serializable
    data class ContentDetailRoute(
        val id: String,
        val mediaCategory: MediaCategory
    ) : SearchRoute
}