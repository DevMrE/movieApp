package com.kmp.movieapp.content_detail.presentation.model

import androidx.compose.ui.text.AnnotatedString

data class ContentDetailUi(
    val isLoading: Boolean = false,
    val title: String,
    val mediaInfo: AnnotatedString,
    val description: String,
    val posterPath: String,
    val videoThumbnail: String,
)
