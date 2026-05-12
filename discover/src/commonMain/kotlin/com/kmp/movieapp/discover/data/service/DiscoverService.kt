package com.kmp.movieapp.discover.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.discover.data.model.response.DiscoverMoviesDto
import com.kmp.movieapp.discover.data.model.response.DiscoverSeriesDto

internal interface DiscoverService {

    suspend fun getDiscoverMovies(page: Int = 1): Result<ApiResponseDto<DiscoverMoviesDto>, ApiError>

    suspend fun getDiscoverSeries(page: Int = 1): Result<ApiResponseDto<DiscoverSeriesDto>, ApiError>
}