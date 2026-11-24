package com.kmp.movieapp.search.domain.usecase

import com.kmp.movieapp.search.domain.model.Browse
import com.kmp.movieapp.search.domain.model.discover.Discover
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchOrDiscoverUseCase(
    private val searchUseCase: SearchUseCase,
    private val getSearchedMediaUseCase: GetSearchedMediaUseCase
) {

    suspend operator fun invoke(browse: Browse): Flow<Browse> {
        val query = browse.query?.takeIf { it.isNotBlank() }

        return if (query != null) {
            searchUseCase(query).map {
                browse.copy(search = it)
            }
        } else {
            getSearchedMediaUseCase(
                browse.page,
                browse.discover?.filter
            ).map {
                browse.copy(
                    discover = Discover(
                        filter = browse.discover?.filter,
                        discoverContent = it
                    )
                )
            }
        }
    }
}