package com.kmp.movieapp.discover.data.service

import com.kmp.movieapp.core.network.http.HandleError
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.movieapp.discover.data.model.request.DiscoverMoviesRequestDto
import com.kmp.movieapp.discover.data.model.request.DiscoverSeriesRequestDto
import com.kmp.movieapp.discover.data.model.response.DiscoverMoviesDto
import com.kmp.movieapp.discover.data.model.response.DiscoverSeriesDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.resources.get

internal class DiscoverServiceImpl(
    private val httpClient: HttpClient
) : DiscoverService {

    override suspend fun getDiscoverMovies(page: Int): Result<ApiResponseDto<DiscoverMoviesDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = DiscoverMoviesRequestDto(page = page))

                Result.Success(response.body<ApiResponseDto<DiscoverMoviesDto>>())
            },
            handlers = mapOf(
                listOf(ClientRequestException::class, ServerResponseException::class) to { e ->
                    val ex = e as ResponseException
                    HandleError.getResultForHttpStatus(ex.response.status)
                },
                listOf(Exception::class) to { e ->
                    logE<DiscoverService>(message = "Error during fetchMoviesForCategory: ${e.message}")
                    HandleError.getResultForHttpStatus(null)
                }
            )
        )


    override suspend fun getDiscoverSeries(page: Int): Result<ApiResponseDto<DiscoverSeriesDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = DiscoverSeriesRequestDto(page = page))

                Result.Success(response.body<ApiResponseDto<DiscoverSeriesDto>>())
            },
            handlers = mapOf(
                listOf(ClientRequestException::class, ServerResponseException::class) to { e ->
                    val ex = e as ResponseException
                    HandleError.getResultForHttpStatus(ex.response.status)
                },
                listOf(Exception::class) to { e ->
                    logE<DiscoverService>(message = "Error during fetchMoviesForCategory: ${e.message}")
                    HandleError.getResultForHttpStatus(null)
                }
            )
        )
}