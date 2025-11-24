package com.kmp.movieapp.movie.data.service

import com.kmp.movieapp.core.data.model.ApiResponseDto
import com.kmp.movieapp.movie.data.model.request.MovieListCategory
import com.kmp.movieapp.movie.data.model.response.MovieDto
import com.kmp.movieapp.movie.data.model.response.MovieGenreResponseDto

interface MovieService {

    suspend fun fetchMoviesForCategory(language: String, page: Int, movieListCategory: MovieListCategory): Result<ApiResponseDto<MovieDto>>

    suspend fun fetchMovieGenres(language: String): Result<MovieGenreResponseDto>
}
