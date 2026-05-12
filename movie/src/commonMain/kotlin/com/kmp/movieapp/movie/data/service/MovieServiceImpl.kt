package com.kmp.movieapp.movie.data.service

import com.kmp.movieapp.core.network.http.HandleError
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.core.util.try_catch.handler
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.movieapp.movie.data.model.request.genre.MovieGenreRequestDto
import com.kmp.movieapp.movie.data.model.request.movie.MovieRequestDto
import com.kmp.movieapp.movie.data.model.request.movie_lists.MovieListRequestDto
import com.kmp.movieapp.movie.data.model.response.MovieGenreResponseDto
import com.kmp.movieapp.movie.data.model.response.movie.MovieDto
import com.kmp.movieapp.movie.data.model.response.movie_for_category.MovieForCategoryDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.resources.get

internal class MovieServiceImpl(
    private val httpClient: HttpClient
) : MovieService {

    override suspend fun fetchMoviesPopularMovies(page: Int): Result<ApiResponseDto<MovieForCategoryDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = MovieListRequestDto(page = page))

                Result.Success(response.body<ApiResponseDto<MovieForCategoryDto>>())
            },

            handler<ResponseException, Result<ApiResponseDto<MovieForCategoryDto>, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<ApiResponseDto<MovieForCategoryDto>, ApiError>> { e ->
                logE<MovieService>(message = "Error during fetchMoviesPopularMovies: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )

    override suspend fun fetchMovieGenres(): Result<MovieGenreResponseDto?, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = MovieGenreRequestDto)

                Result.Success(response.body())
            },

            handler<ResponseException, Result<MovieGenreResponseDto, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<MovieGenreResponseDto, ApiError>> { e ->
                logE<MovieService>(message = "Error during fetchMovieGenres: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )

    override suspend fun findMovieForId(
        movieId: Int
    ): Result<MovieDto, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = MovieRequestDto(movieId = movieId))

                Result.Success(response.body())
            },
            handler<ResponseException, Result<MovieDto, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<MovieDto, ApiError>> { e ->
                logE<MovieService>(message = "Error during findMovieForId: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )
}