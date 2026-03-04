package com.kmp.movieapp.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.detail.presentation.destination.DetailDestination
import com.kmp.movieapp.core.util.action.Action
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.domain.usecase.GetMoviesForCategoryUseCase
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.content.destination.PopularMovieDestination
import com.kmp.movieapp.movie.presentation.mapper.toUiMovieList
import com.kmp.movieapp.movie.presentation.model.UiMovieScreen
import com.kmp.navigation.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieScreenViewModel(
    private val navigation: Navigation,
    private val getMoviesForCategoryUseCase: GetMoviesForCategoryUseCase
) : ViewModel() {

    private val _movieScreenState = MutableStateFlow<UiMovieScreen?>(null)

    val movieScreenState = _movieScreenState.asStateFlow()

    init {
        viewModelScope.launch {
            getMoviesForCategoryUseCase().collectLatest { (popular, topRated, nowPlaying) ->
                _movieScreenState.update {
                    UiMovieScreen(
                        isLoading = popular.isEmpty() || topRated.isEmpty() || nowPlaying.isEmpty(),
                        nowPlaying = nowPlaying.toUiMovieList(category = MovieCategory.NOW_PLAYING),
                        popularMovie = popular.toUiMovieList(category = MovieCategory.POPULAR),
                        topRatedMovies = topRated.toUiMovieList(category = MovieCategory.TOP_RATED)
                    )
                }
            }
        }
    }

    fun onAction(action: Action) {
        when (action) {
            is MovieAction.OnNavigateToDetailScreen -> navigateToDetailScreen(action.id)
            is MovieAction.OnStartTrailer -> Unit
            is MovieAction.OnSeeAllClicked -> onSeeAll()
        }
    }

    private fun navigateToDetailScreen(id: String) {
        navigation.navigateTo(DetailDestination(id))
    }

    private fun onSeeAll() {
        navigation.navigateTo(PopularMovieDestination)
    }
}