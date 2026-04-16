package com.kmp.movieapp.features.home.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.features.home.data.model.request.movie_lists.MovieListCategory
import com.kmp.movieapp.features.home.data.model.response.MovieGenreResponseDto
import com.kmp.movieapp.features.home.data.model.response.discover.DiscoverMovieDto
import com.kmp.movieapp.features.home.data.model.response.movie.MovieDto
import com.kmp.movieapp.features.home.data.model.response.movie_for_category.MovieForCategoryDto
import com.kmp.movieapp.features.home.domain.model.Filter

internal interface MovieService {

    suspend fun fetchMoviesForCategory(
        page: Int,
        movieListCategory: MovieListCategory
    ): Result<ApiResponseDto<MovieForCategoryDto>, ApiError>

    suspend fun fetchMovieGenres(language: String): Result<MovieGenreResponseDto?, ApiError>

    suspend fun fetchAllMovies(filter: Filter): Result<ApiResponseDto<DiscoverMovieDto>, ApiError>

    suspend fun findMovieForId(movieId: Int, language: String): Result<MovieDto, ApiError>
}
