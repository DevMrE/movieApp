package com.kmp.movieapp.features.home.presentation.action

import com.kmp.movieapp.features.movie.data.domain.model.HomeCategory

internal sealed interface HomeAction {

    data class OnNavigateToDetailScreen(val id: String) : HomeAction

    data class OnSeeAllClicked(val homeCategory: HomeCategory?) : HomeAction

    data object OnRefresh : HomeAction
}