package com.kmp.series.domain.model

data class Series(
    val id: Int? = null,
    val name: String? = null,
    val adult: Boolean? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val firstAirDate: String? = null,
    val backdropPath: String? = null,
)
