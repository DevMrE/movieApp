package com.kmp.movieapp.features.series.domain.repository

import com.kmp.movieapp.features.series.domain.model.Series
import kotlinx.coroutines.flow.Flow

internal interface SeriesRepository {

    suspend fun getPopularSeries(page: Int = 1): Flow<List<Series>>

}