package com.kmp.movieapp.features.home.domain.model

/**
 * Data class for representing the possible filter options.
 */
data class Filter(
    val sortBy: SortBy = SortBy.POPULARITY_DESC,
    val page: Int = 1,
    val includeAdult: Boolean = false,
    val includeVideo: Boolean = false,
    val year: Int? = null
)
