package com.kmp.movieapp.movie.presentation

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.movie.data.domain.usecase.GetPopularMoviesUseCase
import com.kmp.movieapp.movie.presentation.mapper.toUiMovieCard
import com.kmp.movieapp.movie.presentation.model.UiPopularMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.updateAndGet

class MovieListViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _uiPopularMovies = MutableStateFlow(UiPopularMovies())

    @OptIn(ExperimentalCoroutinesApi::class)
    val popularMoviesState: StateFlow<UiPopularMovies> = _uiPopularMovies
        .flatMapLatest {
            getPopularMoviesUseCase()
        }.map { popularMovieList ->
            _uiPopularMovies.updateAndGet {
                it.copy(
                    isLoading = popularMovieList.isNullOrEmpty() || popularMovieList.any { movie -> movie.posterPath.isEmpty() },
                    movieList = popularMovieList?.map { movie -> movie.toUiMovieCard() }
                        ?: emptyList()
                )
            }
        }.stateInEagerly(_uiPopularMovies.value)
}