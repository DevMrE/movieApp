package com.kmp.movieapp.movie.data.service

import com.kmp.movieapp.core.data.model.ApiResponseDto
import com.kmp.movieapp.movie.data.model.response.MovieDto
import com.kmp.movieapp.movie.data.model.response.MovieGenreResponseDto

interface MovieService {

    suspend fun getPopularMovies(language: String, page: Int): ApiResponseDto<MovieDto>?

    suspend fun getMovieGenres(language: String): Result<MovieGenreResponseDto>
}
