package com.kmp.movieapp.search.domain.repository

import com.kmp.movieapp.search.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getSearchedItems(query: String): Flow<List<Search>>
}