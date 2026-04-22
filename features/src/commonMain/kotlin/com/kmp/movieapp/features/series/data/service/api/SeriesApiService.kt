package com.kmp.movieapp.features.series.data.service.api

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.features.series.data.model.api.response.SeriesResultDto

interface SeriesApiService {

    suspend fun fetchPopularSeries(page: Int = 1): Result<ApiResponseDto<SeriesResultDto>, ApiError>
}