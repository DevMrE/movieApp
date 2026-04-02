package com.kmp.movieapp.search.domain.model

import com.kmp.movieapp.core.content_type.model.ContentDetailType

internal data class Search(
    val id: String,
    val contentDetailType: ContentDetailType,
    val titleInfo: Info,
    val media: MediaImages,
    val releaseDate: String?,
    val genres: List<Long>,
    val rating: Rating,
)
