package com.kmp.movieapp.search.data.repository

import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.search.data.mapper.mapToSearch
import com.kmp.movieapp.search.data.service.SearchApiService
import com.kmp.movieapp.search.domain.model.Search
import com.kmp.movieapp.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

internal class SearchRepoImpl(
    private val searchApiService: SearchApiService
) : SearchRepository {

    private val _searchQueryState = MutableStateFlow("")
    private val _searchResultState = MutableStateFlow<List<Search>>(emptyList())
    override val searchedItems: StateFlow<List<Search>> = _searchResultState


    override fun getSearchedItems(query: String): Flow<List<Search>> = flow {
        searchApiService.fetchSearch(query)
            .onSuccess { apiResponse ->
                apiResponse.results?.forEach {
                    logI<SearchRepository>(message = "mediaType: ${it.mediaType}")
                }

                val searches =
                    apiResponse.results
                        ?.filter { it.posterPath != null }
                        ?.distinctBy { it.id }
                        ?.distinctBy { it.originalTitle }
                        ?.mapToSearch()
                        ?: emptyList()

                _searchResultState.update {
                    searches
                }

                emit(searches)
            }
    }

    override fun updateSearch(query: String) {
        _searchQueryState.update { query }
    }

}