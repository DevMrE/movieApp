package com.kmp.movieapp.discover.domain.model

data class Discover(
    val filter: Filter? = null,
    val discoverContent: List<DiscoverContent>? = null,
)
