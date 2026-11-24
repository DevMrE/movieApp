package com.kmp.movieapp.overview.presentation

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.overview.domain.model.OverViewMedia
import com.kmp.movieapp.overview.domain.usecase.LoadOverviewMediaList
import com.kmp.movieapp.overview.presentation.mapper.toUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.update

internal class OverviewListViewModel(
    private val loadOverviewMediaList: LoadOverviewMediaList,
    private val mediaCategory: MediaCategory
) : ViewModel() {
    private val _currentPage = MutableStateFlow(mapOf(mediaCategory to 1))

    @OptIn(ExperimentalCoroutinesApi::class)
    val overviewUiState: StateFlow<OverviewUiState> = _currentPage
        .map { it[mediaCategory] ?: 1 }
        .flatMapLatest { page ->
            loadOverviewMediaList(
                mediaCategory = mediaCategory,
                page = page
            )
        }.scan(emptyList<OverViewMedia>()) { currentList, newList ->
            currentList + newList
        }.map { list ->
            list.toUiState()
        }
        .stateInEagerly(OverviewUiState())


    fun loadNextItems() {
        _currentPage.update { map ->
            val current = map[mediaCategory] ?: 1
            map + (mediaCategory to current + 1)
        }
    }
}