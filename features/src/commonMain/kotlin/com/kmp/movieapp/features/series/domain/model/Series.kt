package com.kmp.movieapp.features.series.domain.model

import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.core.content_type.model.Media
import com.kmp.movieapp.features.genre.domain.model.Genre

data class Series(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val genres: List<Genre>? = null,
    val type: ContentDetailType = ContentDetailType.SERIES
) : Media(contentDetailType = type)