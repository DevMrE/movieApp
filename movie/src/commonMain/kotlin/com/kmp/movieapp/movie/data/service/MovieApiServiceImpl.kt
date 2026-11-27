package com.kmp.movieapp.movie.data.service

import com.kmp.movieapp.core.network.http.HandleError
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.core.util.try_catch.handler
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.movieapp.movie.data.model.request.discover.FilterMoviesRequest
import com.kmp.movieapp.movie.data.model.request.movie.MovieRequest
import com.kmp.movieapp.movie.data.model.request.movie_lists.PopularMovieRequest
import com.kmp.movieapp.movie.data.model.response.discover.FilterMovieResponse
import com.kmp.movieapp.movie.data.model.response.movie.MovieResponse
import com.kmp.movieapp.movie.data.model.response.popular_movie.PopularMovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.resources.get

internal class MovieApiServiceImpl(
    private val httpClient: HttpClient
) : MovieApiService {

    override suspend fun fetchPopularMovies(page: Int): Result<List<PopularMovieResponse>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = PopularMovieRequest(page = page))
                val data = checkNotNull(response.body<ApiResponseDto<PopularMovieResponse>>())
                val movieList = checkNotNull(data.results)
                Result.Success(movieList)
            },

            handler<ResponseException, Result<List<PopularMovieResponse>, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<List<PopularMovieResponse>, ApiError>> { e ->
                logE<MovieApiService>(message = "Error during fetchMoviesPopularMovies: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )

    override suspend fun findMovieForId(
        movieId: Int
    ): Result<MovieResponse, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = MovieRequest(movieId = movieId))

                Result.Success(response.body())
            },
            handler<ResponseException, Result<MovieResponse, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<MovieResponse, ApiError>> { e ->
                logE<MovieApiService>(message = "Error during findMovieForId: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )

    override suspend fun fetchDiscoveredMovies(page: Int): Result<List<FilterMovieResponse>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = FilterMoviesRequest(page = page))

                Result.Success(response.body<List<FilterMovieResponse>>())
            },
            handler<ResponseException, Result<List<FilterMovieResponse>, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<List<FilterMovieResponse>, ApiError>> { e ->
                logE<MovieApiService>(message = "Error during findMovieForId: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )
}