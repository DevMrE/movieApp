package com.kmp.movieapp.features.series.data.service.api

import com.kmp.movieapp.core.network.http.HandleError
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.movieapp.features.movie.data.service.MovieApiService
import com.kmp.movieapp.features.series.data.model.api.request.SeriesRequestDto
import com.kmp.movieapp.features.series.data.model.api.response.SeriesResultDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.resources.get

class SeriesApiServiceImpl(
    private val httpClient: HttpClient
) : SeriesApiService {

    override suspend fun fetchPopularSeries(page: Int): Result<ApiResponseDto<SeriesResultDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(
                    resource = SeriesRequestDto(
                        page = page
                    )
                )

                Result.Success(response.body<ApiResponseDto<SeriesResultDto>>())
            },
            handlers = mapOf(
                listOf(ClientRequestException::class, ServerResponseException::class) to { e ->
                    val ex = e as ResponseException
                    HandleError.getResultForHttpStatus(ex.response.status)
                },
                listOf(Exception::class) to { e ->
                    logE<MovieApiService>(message = "Error during fetchMoviesForCategory: ${e.message}")
                    HandleError.getResultForHttpStatus(null)
                }
            )
        )

}