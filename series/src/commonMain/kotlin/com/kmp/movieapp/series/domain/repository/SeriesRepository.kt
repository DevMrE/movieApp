package com.kmp.movieapp.series.domain.repository

import com.kmp.movieapp.series.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    suspend fun getPopularSeries(page: Int = 1): Flow<List<Series>>

    suspend fun getSerieForId(seriesId: Int): Flow<Series>
}