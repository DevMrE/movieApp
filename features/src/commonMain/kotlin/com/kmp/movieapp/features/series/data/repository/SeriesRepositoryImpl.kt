package com.kmp.movieapp.features.series.data.repository

import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.features.series.data.mapper.toSeries
import com.kmp.movieapp.features.series.data.service.api.SeriesApiService
import com.kmp.movieapp.features.series.domain.model.Series
import com.kmp.movieapp.features.series.domain.repository.SeriesRepository
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
}