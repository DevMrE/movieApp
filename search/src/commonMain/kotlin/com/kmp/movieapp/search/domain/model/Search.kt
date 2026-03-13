package com.kmp.movieapp.search.domain.model

internal data class Search(
    val titleInfo: Info,
    val media: MediaImages,
    val releaseDate: String?,
    val genres: List<Long>,
    val rating: Rating,
)
