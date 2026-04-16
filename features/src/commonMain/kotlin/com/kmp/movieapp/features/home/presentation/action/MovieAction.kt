package com.kmp.movieapp.features.home.presentation.action

import com.kmp.movieapp.features.home.domain.model.MovieCategory

internal sealed interface MovieAction {

    data class OnNavigateToDetailScreen(val id: String) : MovieAction

    data class OnStartTrailer(val id: Int) : MovieAction

    data class OnSeeAllClicked(val movieCategory: MovieCategory?) : MovieAction

    data object OnRefresh : MovieAction
}