package com.kmp.movieapp.discover.data.repository

import com.kmp.movieapp.core.network.util.onError
import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.discover.data.mapper.toDiscoverMovies
import com.kmp.movieapp.discover.data.mapper.toDiscoverSeries
import com.kmp.movieapp.discover.data.service.DiscoverService
import com.kmp.movieapp.discover.domain.model.Discover
import com.kmp.movieapp.discover.domain.model.Filter
import com.kmp.movieapp.discover.domain.repository.DiscoverRepository
import com.kmp.movieapp.genre.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class DiscoverRepositoryImpl(
    private val discoverService: DiscoverService,
    private val genreRepository: GenreRepository
) : DiscoverRepository {

    override suspend fun getDiscoverMovies(page: Int, filter: Filter?): Flow<Discover> =
        flow {
            discoverService.fetchDiscoverMovies(
                page,
                genreIds = filter?.genre?.map {
                    "${it.id}"
                }
            ).onSuccess { data ->
                val content = data.results?.map {
                    it.toDiscoverMovies(genreRepository.movieGenres.value)
                }

                emit(Discover(discoverContent = content))
            }.onError {
                logI<DiscoverRepository>("something went wrong by loading discover movies")
            }
        }

    override suspend fun getDiscoverSeries(page: Int, filter: Filter?): Flow<Discover> =
        flow {
            discoverService.fetchDiscoverSeries(page)
                .onSuccess { data ->
                    val content = data.results?.map {
                        it.toDiscoverSeries(genreRepository.seriesGenres.value)
                    }

                    emit(Discover(discoverContent = content))
                }.onError {
                    logI<DiscoverRepository>("something went wrong by loading discover series")
                }
        }
}