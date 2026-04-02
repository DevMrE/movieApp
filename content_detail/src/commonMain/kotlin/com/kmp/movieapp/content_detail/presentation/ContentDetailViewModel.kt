package com.kmp.movieapp.content_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.content_detail.domain.usecase.GetContentDetail
import com.kmp.movieapp.content_detail.presentation.mapper.toUiData
import com.kmp.movieapp.content_detail.presentation.model.ContentDetailUi
import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContentDetailViewModel(
    private val getContentDetail: GetContentDetail,
    private val id: String,
    private val contentType: ContentDetailType
) : ViewModel() {

    private val _uiState: MutableStateFlow<ContentDetailUi?> = MutableStateFlow(value = null)

    val uiState: StateFlow<ContentDetailUi?> = _uiState
        .onStart {
            viewModelScope.launch {
                getContentDetail(
                    contentType = contentType,
                    contentId = id
                ).collectLatest { data ->
                    _uiState.update {
                        data.toUiData()
                    }
                }
            }
        }.stateInEagerly(_uiState.value)
}