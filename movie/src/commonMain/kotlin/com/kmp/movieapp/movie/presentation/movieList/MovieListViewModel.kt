package com.kmp.movieapp.movie.presentation.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.ExperimentalAtomicApi

class MovieListViewModel(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _movieListState: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val movieListState = _movieListState.asStateFlow()

    @OptIn(ExperimentalAtomicApi::class)
    private var currentPage = AtomicInt(1)

    init {
        loadMovies()
    }

    @OptIn(ExperimentalAtomicApi::class)
    private fun loadMovies() {
        currentPage.store(currentPage.load() + 1)

        viewModelScope.launch {
            repository.getMovies(
                language = "de",
                page = currentPage.load(),
                movieCategory = MovieCategory.POPULAR
            ).collectLatest { popularMovies ->
                _movieListState.update {
                    popularMovies
                }
            }
        }
    }
}
