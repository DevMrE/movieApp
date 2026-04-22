package com.kmp.movieapp.features.media_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.features.movie.data.domain.model.HomeCategory
import com.kmp.movieapp.features.movie.data.domain.usecase.LoadNextMoviesForCategoryUseCase
import com.kmp.movieapp.features.movie.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MediaListViewModel(
    private val loadNextMoviesForCategoryUseCase: LoadNextMoviesForCategoryUseCase,
    private val homeCategory: HomeCategory
) : ViewModel() {
    private val _currentPage = MutableStateFlow(1)

    private val _movieListState: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieListState = _movieListState.onStart {
        viewModelScope.launch {
            _currentPage.flatMapLatest { page ->
                loadNextMoviesForCategoryUseCase(
                    page = page,
                    homeCategory = homeCategory
                )
            }.collectLatest { movies ->
                if (movies != null) _movieListState.update { movies }
            }
        }
    }.stateInEagerly(_movieListState.value)

    fun loadNextMovies() {
        _currentPage.update { it + 1 }
    }
}