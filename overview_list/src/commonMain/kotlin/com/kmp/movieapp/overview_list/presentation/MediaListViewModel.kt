package com.kmp.movieapp.overview_list.presentation

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.media_list.presentation.mapper.toMediaList
import com.kmp.movieapp.movie.data.domain.usecase.LoadMediaListForCategoryUseCase
import com.kmp.movieapp.movie.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.update

internal class MediaListViewModel(
    private val loadMediaListForCategoryUseCase: LoadMediaListForCategoryUseCase,
    private val mediaCategory: MediaCategory
) : ViewModel() {
    private val _currentPage = MutableStateFlow(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieListState: StateFlow<List<UiMediaCard>> = _currentPage
        .flatMapLatest { page ->
            loadMediaListForCategoryUseCase(
                page = page
            )
        }.scan(emptyList<Movie>()) { currentList, newList ->
            currentList + (newList ?: emptyList())
        }.map { list ->
            list.toMediaList()
        }
        .stateInEagerly(emptyList())

    fun loadNextMovies() {
        _currentPage.update { it + 1 }
    }
}