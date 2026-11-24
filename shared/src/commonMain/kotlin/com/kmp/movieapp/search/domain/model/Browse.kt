package com.kmp.movieapp.search.domain.model

import com.kmp.movieapp.search.domain.model.discover.Discover

data class Browse(
    val page: Int,
    val query: String? = null,
    val search: List<Search>? = null,
    val discover: Discover? = null,
)
