package com.kmp.movieapp.series.domain.model

import com.kmp.movieapp.core.content_type.model.ContentDetailType

data class Series(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val genres: List<SeriesGenre>? = null,
    val type: ContentDetailType = ContentDetailType.SERIES
)