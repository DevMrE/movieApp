package com.kmp.movieapp.movie.data.service

import com.kmp.movieapp.core.network.http.HandleError
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.core.util.try_catch.handler
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.movieapp.movie.data.model.request.discover.DiscoverMoviesRequest
import com.kmp.movieapp.movie.data.model.request.movie.MovieRequest
import com.kmp.movieapp.movie.data.model.request.movie_lists.MovieListRequest
import com.kmp.movieapp.movie.data.model.response.discover.DiscoverMovieDto
import com.kmp.movieapp.movie.data.model.response.movie.MovieDto
import com.kmp.movieapp.movie.data.model.response.movie_for_category.MovieForCategoryDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.resources.get

internal class MovieApiServiceImpl(
    private val httpClient: HttpClient
) : MovieApiService {

    override suspend fun fetchPopularMovies(page: Int): Result<ApiResponseDto<MovieForCategoryDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = MovieListRequest(page = page))
                val data = checkNotNull(response.body<ApiResponseDto<MovieForCategoryDto>>())
                Result.Success(data)
            },

            handler<ResponseException, Result<ApiResponseDto<MovieForCategoryDto>, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<ApiResponseDto<MovieForCategoryDto>, ApiError>> { e ->
                logE<MovieApiService>(message = "Error during fetchMoviesPopularMovies: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )

    override suspend fun findMovieForId(
        movieId: Int
    ): Result<MovieDto, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = MovieRequest(movieId = movieId))

                Result.Success(response.body())
            },
            handler<ResponseException, Result<MovieDto, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<MovieDto, ApiError>> { e ->
                logE<MovieApiService>(message = "Error during findMovieForId: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )

    override suspend fun fetchDiscoveredMovies(page: Int): Result<ApiResponseDto<DiscoverMovieDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(
                    resource = DiscoverMoviesRequest(
                        page = page
                    )
                )

                Result.Success(response.body<ApiResponseDto<DiscoverMovieDto>>())
            },
            handler<ResponseException, Result<ApiResponseDto<DiscoverMovieDto>, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<ApiResponseDto<DiscoverMovieDto>, ApiError>> { e ->
                logE<MovieApiService>(message = "Error during findMovieForId: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )
}