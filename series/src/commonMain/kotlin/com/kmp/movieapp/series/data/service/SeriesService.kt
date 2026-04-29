package com.kmp.movieapp.series.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.series.data.model.response.series_detail.SeriesDetailDto
import com.kmp.movieapp.series.data.model.response.series_list.SeriesResultDto

interface SeriesService {

    suspend fun fetchPopularSeries(page: Int = 1): Result<ApiResponseDto<SeriesResultDto>, ApiError>

    suspend fun fetchSeriesForId(seriesId: Int): Result<SeriesDetailDto, ApiError>
}