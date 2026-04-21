package com.kmp.movieapp.content_detail.presentation

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.content_detail.domain.usecase.GetContentDetail
import com.kmp.movieapp.content_detail.presentation.mapper.toUiData
import com.kmp.movieapp.content_detail.presentation.model.ContentDetailUi
import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.updateAndGet

class ContentDetailViewModel(
    private val getContentDetail: GetContentDetail,
    private val id: String,
    private val contentType: ContentDetailType
) : ViewModel() {

    private val _uiState: MutableStateFlow<ContentDetailUi?> = MutableStateFlow(value = null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<ContentDetailUi?> = _uiState
        .flatMapLatest {
            getContentDetail(
                contentType = contentType,
                contentId = id
            )
        }.map { data ->
            logI<ContentDetailViewModel>("Data: ${data.posterPath}")
            _uiState.updateAndGet {
                data.toUiData()
            }
        }.stateInEagerly(_uiState.value)
}