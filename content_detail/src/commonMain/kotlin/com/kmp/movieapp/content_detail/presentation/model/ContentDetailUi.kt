package com.kmp.movieapp.content_detail.presentation.model

data class ContentDetailUi(
    val isLoading: Boolean = false,
    val title: String,
    val description: String,
    val posterPath: String,
    val videoThumbnail: String,
)
