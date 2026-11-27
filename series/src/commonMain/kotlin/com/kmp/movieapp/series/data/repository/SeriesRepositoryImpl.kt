package com.kmp.movieapp.series.data.repository

import com.kmp.movieapp.core.network.util.onCompletion
import com.kmp.movieapp.core.network.util.onError
import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.series.data.mapper.toSeries
import com.kmp.movieapp.series.data.mapper.toSeriesList
import com.kmp.movieapp.series.data.service.SeriesService
import com.kmp.movieapp.series.domain.model.Series
import com.kmp.movieapp.series.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class SeriesRepositoryImpl(
    private val seriesService: SeriesService
) : SeriesRepository {

    override suspend fun getPopularSeries(page: Int): Flow<List<Series>> = flow {
        seriesService.fetchPopularSeries(page)
            .onSuccess { seriesResponses ->
                seriesResponses.toSeriesList()
            }.onError {
                emptyList()
            }
            .onCompletion { result ->
                emit(result)
            }
    }

    override suspend fun getSerieForId(seriesId: Int): Flow<Series?> = flow {
        seriesService.fetchSeriesForId(seriesId)
            .onSuccess { response ->
                response.toSeries()
            }.onError {
                null
            }
            .onCompletion { series ->
                emit(series)
            }
    }
}