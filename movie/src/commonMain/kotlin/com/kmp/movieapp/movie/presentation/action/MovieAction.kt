package com.kmp.movieapp.movie.presentation.action

import com.kmp.movieapp.core.util.action.Action
import com.kmp.movieapp.movie.domain.model.MovieCategory

sealed interface MovieAction : Action {

    data class OnNavigateToDetailScreen(val id: String) : MovieAction

    data class OnStartTrailer(val id: Int) : MovieAction

    data class OnSeeAllClicked(val movieCategory: MovieCategory) : MovieAction
}