package com.kmp.movieapp.search.domain.usecase

import com.kmp.movieapp.search.domain.model.Search
import com.kmp.movieapp.search.domain.repository.SearchRepository
import com.kmp.movieapp.search.util.validateSearch
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow

class SearchUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(query: String): Flow<List<Search>> = coroutineScope {
        val search = query.validateSearch()
        return@coroutineScope repository.getSearchedItems(query = search)
    }

}