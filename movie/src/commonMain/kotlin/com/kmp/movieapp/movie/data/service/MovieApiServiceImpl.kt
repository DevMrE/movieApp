package com.kmp.movieapp.movie.data.service

import com.kmp.movieapp.core.network.http.HandleError
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.movieapp.movie.data.model.request.genre.MovieGenreRequestDto
import com.kmp.movieapp.movie.data.model.request.movie.MovieRequestDto
import com.kmp.movieapp.movie.data.model.request.movie_lists.MovieListRequestDto
import com.kmp.movieapp.movie.data.model.response.MovieGenreResponseDto
import com.kmp.movieapp.movie.data.model.response.movie.MovieDto
import com.kmp.movieapp.movie.data.model.response.movie_for_category.MovieForCategoryDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.resources.get

internal class MovieApiServiceImpl(
    private val httpClient: HttpClient
) : MovieApiService {

    override suspend fun fetchMoviesPopularMovies(page: Int): Result<ApiResponseDto<MovieForCategoryDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = MovieListRequestDto(page = page))

                Result.Success(response.body<ApiResponseDto<MovieForCategoryDto>>())
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

    override suspend fun fetchMovieGenres(): Result<MovieGenreResponseDto?, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = MovieGenreRequestDto)

                Result.Success(response.body())
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

    override suspend fun findMovieForId(
        movieId: Int
    ): Result<MovieDto, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = MovieRequestDto(movieId = movieId))

                Result.Success(response.body())
            },
            handlers = mapOf(
                listOf(ClientRequestException::class, ServerResponseException::class) to { e ->
                    val ex = e as ResponseException
                    HandleError.getResultForHttpStatus(ex.response.status)
                },
                listOf(Exception::class) to { e ->
                    logE<MovieApiService>(message = "Error during findMovieForId: ${e.message}")
                    HandleError.getResultForHttpStatus(null)
                }
            )
        )
}