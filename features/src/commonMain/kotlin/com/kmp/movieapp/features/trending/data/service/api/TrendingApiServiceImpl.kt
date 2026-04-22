package com.kmp.movieapp.features.trending.data.service.api

import com.kmp.movieapp.core.network.http.HandleError
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.movieapp.features.movie.data.service.MovieApiService
import com.kmp.movieapp.features.trending.data.model.api.request.TrendingRequestDto
import com.kmp.movieapp.features.trending.data.model.api.request.TrendingRequestTypeDto
import com.kmp.movieapp.features.trending.data.model.api.response.TrendingResultDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.resources.get

internal class TrendingApiServiceImpl(
    private val httpClient: HttpClient
) : TrendingApiService {
    override suspend fun fetchAll(): Result<ApiResponseDto<TrendingResultDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(
                    resource = TrendingRequestDto(trendingRequestType = TrendingRequestTypeDto.ALL)
                )

                Result.Success(response.body())
            },
            handlers = mapOf(
                listOf(
                    ClientRequestException::class,
                    ServerResponseException::class
                ) to { e ->
                    val ex = e as ResponseException
                    HandleError.getResultForHttpStatus(ex.response.status)
                }, listOf(Exception::class) to { e ->
                    logE<MovieApiService>(message = "Error during fetchMoviesForCategory: ${e.message}")
                    HandleError.getResultForHttpStatus(null)
                }
            )
        )

    override suspend fun fetchMovies(): Result<ApiResponseDto<TrendingResultDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(
                    resource = TrendingRequestDto(trendingRequestType = TrendingRequestTypeDto.MOVIE)
                )

                Result.Success(response.body())
            },
            handlers = mapOf(
                listOf(
                    ClientRequestException::class,
                    ServerResponseException::class
                ) to { e ->
                    val ex = e as ResponseException
                    HandleError.getResultForHttpStatus(ex.response.status)
                }, listOf(Exception::class) to { e ->
                    logE<MovieApiService>(message = "Error during fetchMoviesForCategory: ${e.message}")
                    HandleError.getResultForHttpStatus(null)
                }
            )
        )


    override suspend fun fetchSeries(): Result<ApiResponseDto<TrendingResultDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(
                    resource = TrendingRequestDto(trendingRequestType = TrendingRequestTypeDto.SERIES)
                )

                Result.Success(response.body())
            },
            handlers = mapOf(
                listOf(
                    ClientRequestException::class,
                    ServerResponseException::class
                ) to { e ->
                    val ex = e as ResponseException
                    HandleError.getResultForHttpStatus(ex.response.status)
                }, listOf(Exception::class) to { e ->
                    logE<MovieApiService>(message = "Error during fetchMoviesForCategory: ${e.message}")
                    HandleError.getResultForHttpStatus(null)
                }
            )
        )


    override suspend fun fetchPeople(): Result<ApiResponseDto<TrendingResultDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(
                    resource = TrendingRequestDto(trendingRequestType = TrendingRequestTypeDto.PEOPLE)
                )

                Result.Success(response.body())
            },
            handlers = mapOf(
                listOf(
                    ClientRequestException::class,
                    ServerResponseException::class
                ) to { e ->
                    val ex = e as ResponseException
                    HandleError.getResultForHttpStatus(ex.response.status)
                }, listOf(Exception::class) to { e ->
                    logE<MovieApiService>(message = "Error during fetchMoviesForCategory: ${e.message}")
                    HandleError.getResultForHttpStatus(null)
                }
            )
        )
}