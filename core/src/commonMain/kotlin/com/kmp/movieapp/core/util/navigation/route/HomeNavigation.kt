package com.kmp.movieapp.core.util.navigation.route

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeNavigation : AppNavigation {

    @Serializable
    data class SeeAllRoute(val mediaCategory: MediaCategory) : HomeNavigation

    @Serializable
    data class ContentDetail(val id: String, val mediaCategory: MediaCategory) : HomeNavigation
}