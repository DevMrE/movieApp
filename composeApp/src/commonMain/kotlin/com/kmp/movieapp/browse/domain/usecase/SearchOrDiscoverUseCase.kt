package com.kmp.movieapp.browse.domain.usecase

import com.kmp.movieapp.browse.domain.model.Browse
import com.kmp.movieapp.discover.domain.model.Discover
import com.kmp.movieapp.discover.domain.usecase.GetDiscoverUseCase
import com.kmp.movieapp.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchOrDiscoverUseCase(
    private val searchUseCase: SearchUseCase,
    private val getDiscoverUseCase: GetDiscoverUseCase
) {

    suspend operator fun invoke(browse: Browse): Flow<Browse> {
        val query = browse.query?.takeIf { it.isNotBlank() }

        return if (query != null) {
            searchUseCase(query).map {
                browse.copy(search = it)
            }
        } else {
            getDiscoverUseCase(
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