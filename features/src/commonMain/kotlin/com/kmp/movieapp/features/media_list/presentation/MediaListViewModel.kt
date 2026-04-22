package com.kmp.movieapp.features.media_list.presentation

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.features.home.presentation.model.HomeCategory
import com.kmp.movieapp.features.movie.data.domain.usecase.LoadNextMoviesForCategoryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

internal class MediaListViewModel(
    private val loadNextMoviesForCategoryUseCase: LoadNextMoviesForCategoryUseCase,
    private val homeCategory: HomeCategory
) : ViewModel() {
    private val _currentPage = MutableStateFlow(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieListState = _currentPage
        .flatMapLatest { page ->
            loadNextMoviesForCategoryUseCase(
                page = page,
                homeCategory = homeCategory
            )
        }.filterNotNull()
        .stateInEagerly(emptyList())

    fun loadNextMovies() {
        _currentPage.update { it + 1 }
    }
}