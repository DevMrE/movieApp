package com.kmp.movieapp.trending.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.trending.data.model.response.TrendingResultDto

internal interface TrendingApiService {

    suspend fun fetchAll(): Result<ApiResponseDto<TrendingResultDto>, ApiError>

    suspend fun fetchMovies(): Result<ApiResponseDto<TrendingResultDto>, ApiError>

    suspend fun fetchSeries(): Result<ApiResponseDto<TrendingResultDto>, ApiError>

    suspend fun fetchPeople(): Result<ApiResponseDto<TrendingResultDto>, ApiError>
}