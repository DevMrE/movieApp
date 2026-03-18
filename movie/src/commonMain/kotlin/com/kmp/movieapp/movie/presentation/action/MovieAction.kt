package com.kmp.movieapp.movie.presentation.action

import com.kmp.movieapp.movie.domain.model.MovieCategory

internal sealed interface MovieAction {

    data class OnNavigateToDetailScreen(val title: String) : MovieAction

    data class OnStartTrailer(val id: Int) : MovieAction

    data class OnSeeAllClicked(val movieCategory: MovieCategory) : MovieAction
}