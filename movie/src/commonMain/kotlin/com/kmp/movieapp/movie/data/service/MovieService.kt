package com.kmp.movieapp.movie.data.service

import com.kmp.movieapp.core.data.model.ApiError
import com.kmp.movieapp.core.data.model.ApiResponseDto
import com.kmp.movieapp.core.util.network.Result
import com.kmp.movieapp.movie.data.model.request.MovieListCategory
import com.kmp.movieapp.movie.data.model.response.DiscoverMovieDto
import com.kmp.movieapp.movie.data.model.response.MovieDto
import com.kmp.movieapp.movie.data.model.response.MovieGenreResponseDto
import com.kmp.movieapp.movie.domain.model.Filter

interface MovieService {

    suspend fun fetchMoviesForCategory(
        language: String,
        page: Int,
        movieListCategory: MovieListCategory
    ): Result<ApiResponseDto<MovieDto>, ApiError>

    suspend fun fetchMovieGenres(language: String): Result<MovieGenreResponseDto?, ApiError>

    suspend fun fetchAllMovies(filter: Filter): Result<ApiResponseDto<DiscoverMovieDto>, ApiError>
}
