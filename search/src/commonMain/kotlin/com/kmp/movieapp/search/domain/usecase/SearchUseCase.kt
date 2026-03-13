package com.kmp.movieapp.search.domain.usecase

import com.kmp.movieapp.core.util.string.clearInput
import com.kmp.movieapp.search.domain.model.Search
import com.kmp.movieapp.search.domain.repository.SearchRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow

internal class SearchUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(query: String): Flow<List<Search>> = coroutineScope {
        val search = query.clearInput()
        return@coroutineScope repository.getSearchedItems(query = search)
    }

}