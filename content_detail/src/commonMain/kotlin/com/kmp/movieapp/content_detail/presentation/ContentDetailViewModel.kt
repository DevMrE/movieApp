package com.kmp.movieapp.content_detail.presentation

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.content_detail.domain.usecase.GetContentDetail
import com.kmp.movieapp.content_detail.presentation.mapper.toUiData
import com.kmp.movieapp.content_detail.presentation.model.ContentDetailUi
import com.kmp.movieapp.core.domain.model.ContentDetailType
import com.kmp.movieapp.core.util.viewmodel.stateInLazily
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class ContentDetailViewModel(
    private val getContentDetail: GetContentDetail
) : ViewModel() {

    private val _uiState: MutableStateFlow<ContentDetailUi> = MutableStateFlow(
        ContentDetailUi(
            title = "",
            posterPath = "",
            videoThumbnail = "",
            description = ""
        )
    )

    fun uiState(id: String): StateFlow<ContentDetailUi> = _uiState.onStart {
        //val contentID = savedStateHandle.get<String>(Constants.CONTENT_DETAIL_KEY) ?: return@onStart
        getContentDetail(
            contentType = ContentDetailType.MOVIE,
            contentId = id
        ).collectLatest { data ->
            _uiState.update {
                data.toUiData()
            }
        }
    }.stateInLazily(_uiState.value)
}