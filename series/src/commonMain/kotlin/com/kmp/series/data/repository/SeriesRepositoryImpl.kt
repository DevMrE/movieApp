package com.kmp.series.data.repository

import com.kmp.movieapp.core.network.util.onError
import com.kmp.movieapp.core.network.util.onFailure
import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.series.data.mapper.toSeries
import com.kmp.series.data.service.SeriesService
import com.kmp.series.domain.model.Series
import com.kmp.series.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class SeriesRepositoryImpl(
    private val service: SeriesService
) : SeriesRepository{
    override fun getSeriesForId(seriesId: Int, language: String): Flow<Series?> = flow {
        service.findSeriesForId(seriesId = seriesId, language = language)
            .onSuccess {
                emit(it.toSeries())
            }.onError {
                logE<SeriesRepository>(message = "Error by loading series for id: $service | error: $it")
            }.onFailure {
                emit(null)
            }
    }
}