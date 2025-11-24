package com.kmp.movieapp.movie.presentation.action

import com.kmp.movieapp.core.presentation.action.Action

sealed interface MovieAction {

    data class OnNavigateToDetailScreen(val id: Int) : Action
}