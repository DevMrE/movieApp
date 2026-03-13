package com.kmp.movieapp.movie.data.service

import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.data.http.HandleHttpStatus
import com.kmp.movieapp.core.data.model.ApiError
import com.kmp.movieapp.core.data.model.ApiResponseDto
import com.kmp.movieapp.core.util.network.Result
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
import io.ktor.http.HttpStatusCode

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
                    language = "de",
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
                    messageString = "Error during fetchMoviesForCategory",
                    throwable = e
                )
                HandleHttpStatus.getResultForStatus(null)
            }
        )
    )

    override suspend fun fetchMovieGenres(language: String): Result<MovieGenreResponseDto?, ApiError> =
        try {
            val response = httpClient.get(
                resource = MovieGenreRequestDto(language = language)
            )

            if (response.status == HttpStatusCode.OK) {
                Result.Success(response.body())
            } else Result.Failure(value = ApiError.NotFound)

        } catch (e: Exception) {
            Logger.e(messageString = e.message.toString())
            Result.Failure(e.message)
        }

    override suspend fun fetchAllMovies(filter: Filter): Result<ApiResponseDto<DiscoverMovieDto>, ApiError> =
        try {
            val response = httpClient.get(
                resource = filter.toDiscoverMoviesDto()
            )

            if (response == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Failure(value = ApiError.NotFound)
        } catch (e: Exception) {
            Logger.e(messageString = e.message.toString())
            Result.Failure(e.message)
        }

}