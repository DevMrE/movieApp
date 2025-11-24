package com.kmp.movieapp.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.presentation.action.Action
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.mapper.toUiMovie
import com.kmp.movieapp.movie.presentation.model.UiMovie
import com.kmp.movieapp.movie.presentation.route.MovieDetailDestination
import com.kmp.navigation.navigation.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieScreenViewModel(
    private val navigation: Navigation,
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _moviesState = MutableStateFlow<List<UiMovie>>(emptyList())
    val movieState = _moviesState.asStateFlow()

    init {
        viewModelScope.launch {
            val list = movieRepository.getMovies().map {
                it.toUiMovie()
            }
            _moviesState.update { list }
        }
    }

    fun onAction(action: Action) {
        when (action) {
            is MovieAction.OnNavigateToDetailScreen -> navigateToDetailScreen(action.id)
            else -> Unit
        }
    }

    private fun navigateToDetailScreen(id: Int) {
        navigation.navigateTo(MovieDetailDestination(id = id))
    }

}