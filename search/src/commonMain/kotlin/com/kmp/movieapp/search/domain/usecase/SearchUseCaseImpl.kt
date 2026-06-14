package com.kmp.movieapp.search.domain.usecase

import com.kmp.movieapp.core.util.string.clearInput
import com.kmp.movieapp.search.domain.model.Search
import com.kmp.movieapp.search.domain.repository.SearchRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow

internal class SearchUseCaseImpl(
    private val repository: SearchRepository
) : SearchUseCase {

    override suspend operator fun invoke(query: String): Flow<List<Search>> = coroutineScope {
        val search = query.clearInput()
        return@coroutineScope repository.getSearchedItems(query = search)
    }

}