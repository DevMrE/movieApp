package com.kmp.movieapp.features.movie.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.features.movie.data.domain.model.Filter
import com.kmp.movieapp.features.movie.data.model.response.MovieGenreResponseDto
import com.kmp.movieapp.features.movie.data.model.response.discover.DiscoverMovieDto
import com.kmp.movieapp.features.movie.data.model.response.movie.MovieDto
import com.kmp.movieapp.features.movie.data.model.response.movie_for_category.MovieForCategoryDto

internal interface MovieApiService {

    suspend fun fetchMoviesPopularMovies(
        page: Int,
    ): Result<ApiResponseDto<MovieForCategoryDto>, ApiError>

    suspend fun fetchMovieGenres(): Result<MovieGenreResponseDto?, ApiError>

    suspend fun fetchAllMovies(filter: Filter): Result<ApiResponseDto<DiscoverMovieDto>, ApiError>

    suspend fun findMovieForId(movieId: Int): Result<MovieDto, ApiError>
}
