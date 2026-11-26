package com.kmp.movieapp.search.domain.usecase

import com.kmp.movieapp.search.domain.model.Filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchOrDiscoverUseCase(
    private val searchUseCase: SearchUseCase,
    private val getSearchedMediaUseCase: GetSearchedMediaUseCase
) {

    suspend operator fun invoke(filter: Filter): Flow<Filter> {
        val query = filter.query?.takeIf { it.isNotBlank() }

        return if (query != null) {
            searchUseCase(query).map {
                filter.copy(query = "")
            }
        } else {
            getSearchedMediaUseCase(
                filter.page,
                filter.filterType
            ).map {
                filter.copy(
                    filterType = filter.filterType,
                    searchResult = it
                )
            }
        }
    }
}