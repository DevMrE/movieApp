package com.kmp.movieapp.movie.presentation.action

import com.kmp.movieapp.core.util.action.Action

sealed interface MovieAction : Action {

    data class OnNavigateToDetailScreen(val id: String) : MovieAction

    data class OnStartTrailer(val id: Int) : MovieAction

    data object OnSeeAllClicked : MovieAction
}