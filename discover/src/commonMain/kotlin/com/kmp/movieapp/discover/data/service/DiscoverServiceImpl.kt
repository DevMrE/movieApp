package com.kmp.movieapp.discover.data.service

import com.kmp.movieapp.core.network.http.HandleError
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.core.util.try_catch.handler
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.movieapp.discover.data.model.request.DiscoverMoviesRequestDto
import com.kmp.movieapp.discover.data.model.request.DiscoverSeriesRequestDto
import com.kmp.movieapp.discover.data.model.response.DiscoverMoviesDto
import com.kmp.movieapp.discover.data.model.response.DiscoverSeriesDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.resources.get

internal class DiscoverServiceImpl(
    private val httpClient: HttpClient
) : DiscoverService {

    override suspend fun fetchDiscoverMovies(
        page: Int,
        genreIds: List<String>?
    ): Result<ApiResponseDto<DiscoverMoviesDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(
                    resource = DiscoverMoviesRequestDto(
                        page = page,
                        genres = genreIds?.joinToString(",")
                    )
                )

                Result.Success(response.body<ApiResponseDto<DiscoverMoviesDto>>())
            },
            handler<ResponseException, Result<ApiResponseDto<DiscoverMoviesDto>, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<ApiResponseDto<DiscoverMoviesDto>, ApiError>> { e ->
                logE<DiscoverService>(message = "Error during fetchDiscoverMovies: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )


    override suspend fun fetchDiscoverSeries(page: Int): Result<ApiResponseDto<DiscoverSeriesDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = DiscoverSeriesRequestDto(page = page))

                Result.Success(response.body<ApiResponseDto<DiscoverSeriesDto>>())
            },
            handler<ResponseException, Result<ApiResponseDto<DiscoverSeriesDto>, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<ApiResponseDto<DiscoverSeriesDto>, ApiError>> { e ->
                logE<DiscoverService>(message = "Error during fetchDiscoverSeries: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )
}