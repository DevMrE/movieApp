package com.kmp.movieapp.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.presentation.action.Action
import com.kmp.movieapp.core.presentation.viewmodel.stateInLazily
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.domain.usecase.GetMoviesForCategoryUseCase
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.mapper.toUiMovieList
import com.kmp.movieapp.movie.presentation.model.UiMovieScreen
import com.kmp.movieapp.movie.presentation.route.MovieDetailDestination
import com.kmp.navigation.navigation.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class MovieScreenViewModel(
    private val navigation: Navigation,
    private val getMoviesForCategoryUseCase: GetMoviesForCategoryUseCase
) : ViewModel() {

    private val _movieScreenState = MutableStateFlow<UiMovieScreen?>(null)

    val movieScreenState = _movieScreenState.onStart {
        combine(
            getMoviesForCategoryUseCase(movieCategory = MovieCategory.POPULAR),
            getMoviesForCategoryUseCase(movieCategory = MovieCategory.TOP_RATED),
            getMoviesForCategoryUseCase(movieCategory = MovieCategory.NOW_PLAYING)
        ) { popular, topRated, nowPlaying ->
            _movieScreenState.update {
                UiMovieScreen(
                    isLoading = popular.isEmpty() || topRated.isEmpty() || nowPlaying.isEmpty(),
                    nowPlaying = nowPlaying.toUiMovieList(category = MovieCategory.NOW_PLAYING),
                    popularMovie = popular.toUiMovieList(category = MovieCategory.POPULAR),
                    topRatedMovies = topRated.toUiMovieList(category = MovieCategory.TOP_RATED)
                )
            }
        }.launchIn(viewModelScope)
    }.stateInLazily(_movieScreenState.value)

    fun onAction(action: Action) {
        when (action) {
            is MovieAction.OnNavigateToDetailScreen -> navigateToDetailScreen(action.id)
            is MovieAction.OnStartTrailer -> Unit
        }
    }

    private fun navigateToDetailScreen(id: Int) {
        navigation.navigateTo(MovieDetailDestination(id = id))
    }

}