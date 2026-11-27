package com.kmp.movieapp.movie.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.movie.data.model.response.discover.FilterMovieResponse
import com.kmp.movieapp.movie.data.model.response.movie.MovieResponse
import com.kmp.movieapp.movie.data.model.response.popular_movie.PopularMovieResponse

internal interface MovieApiService {

    suspend fun fetchPopularMovies(page: Int): Result<List<PopularMovieResponse>, ApiError>

    suspend fun findMovieForId(movieId: Int): Result<MovieResponse, ApiError>

    suspend fun fetchDiscoveredMovies(page: Int): Result<List<FilterMovieResponse>, ApiError>
}
