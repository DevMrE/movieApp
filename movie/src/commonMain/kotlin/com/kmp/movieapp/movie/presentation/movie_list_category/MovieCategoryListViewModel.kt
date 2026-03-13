package com.kmp.movieapp.movie.presentation.movie_list_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.domain.usecase.LoadNextMoviesForCategoryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MovieCategoryListViewModel(
    private val loadNextMoviesForCategoryUseCase: LoadNextMoviesForCategoryUseCase,
    private val movieCategory: MovieCategory
) : ViewModel() {
    private val _currentPage = MutableStateFlow(1)

    private val _movieListState: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieListState = _movieListState.onStart {
        viewModelScope.launch {
            _currentPage.flatMapLatest { page ->
                loadNextMoviesForCategoryUseCase(
                    page = page,
                    movieCategory = movieCategory
                )
            }.collectLatest { movies ->
                _movieListState.update { movies }
            }
        }
    }.stateInEagerly(_movieListState.value)

    fun loadNextMovies() {
        _currentPage.update { it + 1 }
    }
}
