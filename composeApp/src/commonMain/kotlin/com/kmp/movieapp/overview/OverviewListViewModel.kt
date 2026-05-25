package com.kmp.movieapp.overview

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.overview.mapper.toUiMediaCardList
import com.kmp.movieapp.overview_list.domain.model.OverViewMedia
import com.kmp.movieapp.overview_list.domain.usecase.LoadMediaListForCategoryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.update

internal class OverviewListViewModel(
    private val loadMediaListForCategoryUseCase: LoadMediaListForCategoryUseCase,
    private val mediaCategory: MediaCategory
) : ViewModel() {
    private val _currentPage = MutableStateFlow(mapOf(mediaCategory to 1))

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieListState: StateFlow<List<UiMediaCard>> = _currentPage
        .map { it[mediaCategory] ?: 1 }
        .flatMapLatest { page ->
            loadMediaListForCategoryUseCase(
                mediaCategory = mediaCategory,
                page = page
            )
        }.scan(emptyList<OverViewMedia>()) { currentList, newList ->
            currentList + (newList)
        }.map { list ->
            list.toUiMediaCardList()
                .distinctBy { it.id }
        }
        .stateInEagerly(emptyList())

    fun loadNextMovies() {
        _currentPage.update { map ->
            val current = map[mediaCategory] ?: 1
            map + (mediaCategory to current + 1)
        }
    }
}