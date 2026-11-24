package com.kmp.movieapp.search.domain.model.discover

data class Discover(
    val filter: Filter? = null,
    val discoverContent: List<DiscoverContent>? = null,
)
