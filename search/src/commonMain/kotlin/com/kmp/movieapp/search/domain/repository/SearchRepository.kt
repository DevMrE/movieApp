package com.kmp.movieapp.search.domain.repository

import com.kmp.movieapp.search.domain.model.Search
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to search results from the domain layer.
 * Abstracts data sources (remote, cache, local) and exposes unified search data.
 */
internal interface SearchRepository {

    /**
     * Retrieves search results matching the given query.
     *
     * @param query The search term.
     * @return A reactive stream of [Search] items corresponding to the query.
     */
    fun getSearchedItems(query: String): Flow<List<Search>>
}