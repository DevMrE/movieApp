package com.kmp.movieapp.series.data.repository

import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.series.data.mapper.toSeries
import com.kmp.movieapp.series.data.service.api.SeriesApiService
import com.kmp.movieapp.series.domain.model.Series
import com.kmp.movieapp.series.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class SeriesRepositoryImpl(
    private val seriesApiService: SeriesApiService
) : SeriesRepository {

    override suspend fun getPopularSeries(page: Int): Flow<List<Series>> = flow {
        seriesApiService.fetchPopularSeries(page)
            .onSuccess { dto ->
                val series = dto.results?.map { it.toSeries() } ?: emptyList()
                emit(series)
            }
    }

    override suspend fun getSerieForId(seriesId: String): Flow<Series> = flow {
        seriesApiService.fetchSeriesForId(seriesId.toInt())
            .onSuccess { dto ->
                val series =  dto.toSeries()
                emit(series)
            }
    }
}