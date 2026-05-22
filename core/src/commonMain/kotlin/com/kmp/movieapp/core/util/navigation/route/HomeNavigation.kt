package com.kmp.movieapp.core.util.navigation.route

import com.kmp.movieapp.core.ui.content.model.MediaCategory

sealed interface HomeNavigation : AppNavigation {

    data class SeeAllRoute(val mediaCategory: MediaCategory) : HomeNavigation

    data class ContentDetail(val id: String, val mediaCategory: MediaCategory): HomeNavigation
}