package com.kmp.movieapp.series.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.series.data.model.response.series_detail.SeriesResponse

interface SeriesService {

    suspend fun fetchPopularSeries(page: Int = 1): Result<List<SeriesResponse>, ApiError>

    suspend fun fetchSeriesForId(seriesId: Int): Result<SeriesResponse, ApiError>
}