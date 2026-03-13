package com.kmp.movieapp.search.data.repository

import com.kmp.movieapp.core.util.network.alsoOnSuccess
import com.kmp.movieapp.search.data.mapper.mapToSearch
import com.kmp.movieapp.search.data.service.SearchApiService
import com.kmp.movieapp.search.domain.model.Search
import com.kmp.movieapp.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class SearchRepoImpl(
    private val searchApiService: SearchApiService
) : SearchRepository {

    override fun getSearchedItems(query: String): Flow<List<Search>> = flow {
        searchApiService.fetchSearch(query)
            .alsoOnSuccess { apiResponse ->
                val movieTitle =
                    apiResponse.results
                        ?.distinctBy { it.id }
                        ?.distinctBy { it.originalTitle }
                        ?.mapToSearch()
                        ?: emptyList()
                emit(movieTitle)
            }
    }
}