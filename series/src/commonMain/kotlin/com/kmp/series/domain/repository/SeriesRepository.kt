package com.kmp.series.domain.repository

import com.kmp.series.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    fun getSeriesForId(seriesId: Int, language: String = "en"): Flow<Series?>
}