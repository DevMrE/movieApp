package com.kmp.movieapp.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.ui.navigation.MediaDetailDestination
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.domain.usecase.GetMoviesForCategoryUseCase
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.destination.MovieCategoryListDestination
import com.kmp.movieapp.movie.presentation.mapper.toUiMovieList
import com.kmp.movieapp.movie.presentation.model.UiMovieScreen
import com.kmp.navigation.Navigation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

internal class MovieScreenViewModel(
    private val navigation: Navigation,
    private val getMoviesForCategoryUseCase: GetMoviesForCategoryUseCase
) : ViewModel() {

    private val _movieScreenState = MutableStateFlow<UiMovieScreen?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieScreenState = _movieScreenState
        .flatMapLatest {
            getMoviesForCategoryUseCase()
        }.map { (popular, topRated, nowPlaying) ->
            _movieScreenState.updateAndGet {
                UiMovieScreen(
                    isLoading = popular.isEmpty() || topRated.isEmpty() || nowPlaying.isEmpty(),
                    nowPlaying = nowPlaying.toUiMovieList(category = MovieCategory.NOW_PLAYING),
                    popularMovie = popular.toUiMovieList(category = MovieCategory.POPULAR),
                    topRatedMovies = topRated.toUiMovieList(category = MovieCategory.TOP_RATED)
                )
            }
        }.stateInEagerly(_movieScreenState.value)

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

    fun onAction(action: MovieAction) {
        when (action) {
            is MovieAction.OnNavigateToDetailScreen -> navigateToDetailScreen(action.title)
            is MovieAction.OnStartTrailer -> Unit
            is MovieAction.OnSeeAllClicked -> onSeeAll(action.movieCategory)
        }
    }

    private fun navigateToDetailScreen(title: String) {
        navigation.navigateTo(MediaDetailDestination(title))
    }

    private fun onSeeAll(movieCategory: MovieCategory) {
        navigation.navigateTo(destination = MovieCategoryListDestination(movieCategory))
    }
}