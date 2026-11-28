package com.kmp.movieapp.search.domain.model

import com.kmp.movieapp.core.util.mediaCategory.MediaCategory

data class Search(
    val id: String,
    val mediaCategory: MediaCategory,
    val titleInfo: Info,
    val media: MediaImages,
    val releaseDate: String?,
    val genres: List<Long>,
    val rating: Rating,
)
