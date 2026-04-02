package com.kmp.movieapp.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.core.ui.navigation.MediaDetailDestination
import com.kmp.movieapp.core.util.boolean.isFalse
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
                    isLoading = popular?.isEmpty().isFalse || topRated?.isEmpty().isFalse || nowPlaying?.isEmpty().isFalse,
                    nowPlaying = nowPlaying?.toUiMovieList(category = MovieCategory.NOW_PLAYING),
                    popularMovie = popular?.toUiMovieList(category = MovieCategory.POPULAR),
                    topRatedMovies = topRated?.toUiMovieList(category = MovieCategory.TOP_RATED)
                )
            }
        }.stateInEagerly(_movieScreenState.value)

    fun onAction(action: MovieAction) {
        when (action) {
            is MovieAction.OnNavigateToDetailScreen -> navigateToDetailScreen(action.id)
            is MovieAction.OnStartTrailer -> Unit
            is MovieAction.OnSeeAllClicked -> onSeeAll(action.movieCategory)
            is MovieAction.OnRefresh -> onRefresh()
        }
    }

    private fun navigateToDetailScreen(id: String) {
        navigation.navigateTo(
            MediaDetailDestination(
                id = id,
                contentDetailType = ContentDetailType.MOVIE
            )
        )
    }

    private fun onSeeAll(movieCategory: MovieCategory?) {
        if (movieCategory == null) return
        navigation.navigateTo(destination = MovieCategoryListDestination(movieCategory))
    }

    private fun onRefresh() {
        viewModelScope.launch {
            _movieScreenState.update {
                UiMovieScreen(
                    isLoading = true,
                    nowPlaying = _movieScreenState.value?.nowPlaying,
                    popularMovie = _movieScreenState.value?.popularMovie,
                    topRatedMovies = _movieScreenState.value?.topRatedMovies,
                )
            }

            getMoviesForCategoryUseCase().collectLatest { (popular, topRated, nowPlaying) ->
                _movieScreenState.update {
                    UiMovieScreen(
                        isLoading = popular?.isEmpty().isFalse || topRated?.isEmpty().isFalse || nowPlaying?.isEmpty().isFalse,
                        nowPlaying = nowPlaying?.toUiMovieList(category = MovieCategory.NOW_PLAYING),
                        popularMovie = popular?.toUiMovieList(category = MovieCategory.POPULAR),
                        topRatedMovies = topRated?.toUiMovieList(category = MovieCategory.TOP_RATED)
                    )
                }
            }
        }
    }
}