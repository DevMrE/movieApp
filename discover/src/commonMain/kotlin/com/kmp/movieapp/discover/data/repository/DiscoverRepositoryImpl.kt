package com.kmp.movieapp.discover.data.repository

import com.kmp.movieapp.core.network.util.onFailure
import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.discover.data.mapper.toDiscoverMovies
import com.kmp.movieapp.discover.data.mapper.toDiscoverSeries
import com.kmp.movieapp.discover.data.service.DiscoverService
import com.kmp.movieapp.discover.domain.model.Discover
import com.kmp.movieapp.discover.domain.repository.DiscoverRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class DiscoverRepositoryImpl(
    private val discoverService: DiscoverService
) : DiscoverRepository {
    override suspend fun getDiscoverMovies(page: Int): Flow<List<Discover>> =
        flow {
            discoverService.getDiscoverMovies(page)
                .onSuccess { data ->
                    emit(data.results?.map { it.toDiscoverMovies() } ?: emptyList())
                }.onFailure {
                    logI<DiscoverRepository>("something went wrong by loading discover movies")
                }
        }

    override suspend fun getDiscoverSeries(page: Int): Flow<List<Discover>> =
        flow {
            discoverService.getDiscoverSeries(page)
                .onSuccess { data ->
                    emit(data.results?.map { it.toDiscoverSeries() } ?: emptyList())
                }.onFailure {
                    logI<DiscoverRepository>("something went wrong by loading discover series")
                }
        }
}