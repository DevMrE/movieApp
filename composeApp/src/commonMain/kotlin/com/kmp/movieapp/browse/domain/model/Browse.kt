package com.kmp.movieapp.browse.domain.model

import com.kmp.movieapp.discover.domain.model.Discover
import com.kmp.movieapp.search.domain.model.Search

data class Browse(
    val page: Int,
    val query: String? = null,
    val search: List<Search>? = null,
    val discover: Discover? = null,
)
