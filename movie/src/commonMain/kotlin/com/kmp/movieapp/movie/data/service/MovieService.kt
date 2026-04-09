package com.kmp.movieapp.movie.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.movie.data.model.request.movie_lists.MovieListCategory
import com.kmp.movieapp.movie.data.model.response.MovieGenreResponseDto
import com.kmp.movieapp.movie.data.model.response.discover.DiscoverMovieDto
import com.kmp.movieapp.movie.data.model.response.movie.MovieDto
import com.kmp.movieapp.movie.data.model.response.movie_for_category.MovieForCategoryDto
import com.kmp.movieapp.movie.domain.model.Filter

internal interface MovieService {

    suspend fun fetchMoviesForCategory(
        language: String,
        page: Int,
        movieListCategory: MovieListCategory
    ): Result<ApiResponseDto<MovieForCategoryDto>, ApiError>

    suspend fun fetchMovieGenres(language: String): Result<MovieGenreResponseDto?, ApiError>

    suspend fun fetchAllMovies(filter: Filter): Result<ApiResponseDto<DiscoverMovieDto>, ApiError>

    suspend fun findMovieForId(movieId: Int, language: String): Result<MovieDto, ApiError>
}
