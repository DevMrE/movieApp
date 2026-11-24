package com.kmp.movieapp.browse.domain.model.discover

data class Discover(
    val filter: Filter? = null,
    val discoverContent: List<DiscoverContent>? = null,
)
