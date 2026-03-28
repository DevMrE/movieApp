package com.kmp.movieapp.movie.data.service

import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.network.http.HandleHttpStatus
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.movieapp.movie.data.model.mapper.toDiscoverMoviesDto
import com.kmp.movieapp.movie.data.model.request.MovieGenreRequestDto
import com.kmp.movieapp.movie.data.model.request.MovieListCategory
import com.kmp.movieapp.movie.data.model.request.MovieListRequestDto
import com.kmp.movieapp.movie.data.model.response.DiscoverMovieDto
import com.kmp.movieapp.movie.data.model.response.MovieDto
import com.kmp.movieapp.movie.data.model.response.MovieGenreResponseDto
import com.kmp.movieapp.movie.domain.model.Filter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.resources.get

internal class MovieServiceImpl(
    private val httpClient: HttpClient
) : MovieService {

    override suspend fun fetchMoviesForCategory(
        language: String,
        page: Int,
        movieListCategory: MovieListCategory
    ): Result<ApiResponseDto<MovieDto>, ApiError> = multiCatch(
        tryBlock = {
            val response = httpClient.get(
                resource = MovieListRequestDto(
                    page = page,
                    language = language,
                    movieListCategory = movieListCategory.category
                )
            )

            Result.Success(response.body<ApiResponseDto<MovieDto>>())
        },
        handlers = mapOf(
            listOf(ClientRequestException::class, ServerResponseException::class) to { e ->
                val ex = e as ResponseException
                HandleHttpStatus.getResultForStatus(ex.response.status)
            },
            listOf(Exception::class) to { e ->
                Logger.e(
                    tag = "ApiError",
                    messageString = "Error during fetchMoviesForCategory: ${e.message}"
                )
                HandleHttpStatus.getResultForStatus(null)
            }
        )
    )

    override suspend fun fetchMovieGenres(language: String): Result<MovieGenreResponseDto?, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(
                    resource = MovieGenreRequestDto(language = language)
                )

                Result.Success(response.body())
            },
            handlers = mapOf(
                listOf(ClientRequestException::class, ServerResponseException::class) to { e ->
                    val ex = e as ResponseException
                    HandleHttpStatus.getResultForStatus(ex.response.status)
                },
                listOf(Exception::class) to { e ->
                    Logger.e(tag = "ApiError", messageString = "Error during fetchMoviesForCategory: ${e.message}")
                    HandleHttpStatus.getResultForStatus(null)
                }
            )
        )

    override suspend fun fetchAllMovies(filter: Filter): Result<ApiResponseDto<DiscoverMovieDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(
                    resource = filter.toDiscoverMoviesDto()
                )

                Result.Success(response.body())
            },
            handlers = mapOf(
                listOf(
                    ClientRequestException::class,
                    ServerResponseException::class
                ) to { e ->
                    val ex = e as ResponseException
                    HandleHttpStatus.getResultForStatus(ex.response.status)
                },
                listOf(Exception::class) to { e ->
                    Logger.e(
                        tag = "ApiError",
                        messageString = "Error during fetchMoviesForCategory: ${e.message}"
                    )
                    HandleHttpStatus.getResultForStatus(null)
                }
            )
        )
}