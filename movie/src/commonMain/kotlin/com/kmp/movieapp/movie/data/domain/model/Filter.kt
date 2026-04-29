package com.kmp.movieapp.movie.data.domain.model

import com.kmp.movieapp.movie.data.domain.model.SortBy.POPULARITY_DESC

/**
 * Data class for representing the possible filter options.
 */
data class Filter(
    val sortBy: SortBy = POPULARITY_DESC,
    val page: Int = 1,
    val includeAdult: Boolean = false,
    val includeVideo: Boolean = false,
    val year: Int? = null
)
