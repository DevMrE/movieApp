package com.kmp.movieapp.search.domain.model

data class FilterGenre(
    val id: Long,
    val name: String,
    val filterMediaType: FilterMediaType
)