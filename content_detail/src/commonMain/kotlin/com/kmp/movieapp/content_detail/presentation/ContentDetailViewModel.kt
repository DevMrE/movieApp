package com.kmp.movieapp.content_detail.presentation

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.content_detail.domain.usecase.GetContentDetailUseCase
import com.kmp.movieapp.content_detail.presentation.mapper.toUiData
import com.kmp.movieapp.content_detail.presentation.model.UiContentDetailState
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.updateAndGet

internal class ContentDetailViewModel(
    private val id: String,
    private val mediaCategory: MediaCategory,
    private val getContentDetailUseCase: GetContentDetailUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiContentDetailState?> =
        MutableStateFlow(value = UiContentDetailState(isLoading = true))

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<UiContentDetailState?> = _uiState
        .flatMapLatest {
            getContentDetailUseCase(
                contentType = mediaCategory,
                contentId = id
            )
        }.map { data ->
            _uiState.updateAndGet {
                data?.toUiData()
            }
        }.stateInEagerly(_uiState.value)

    override fun onCleared() {
        logI<ContentDetailViewModel>("ViewModel cleared")
        super.onCleared()
        logI<ContentDetailViewModel>("ViewModel cleared")
    }
}