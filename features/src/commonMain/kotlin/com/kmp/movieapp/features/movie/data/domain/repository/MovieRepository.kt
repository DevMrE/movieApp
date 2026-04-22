package com.kmp.movieapp.features.movie.data.domain.repository

import com.kmp.movieapp.features.movie.data.domain.model.HomeCategory
import com.kmp.movieapp.features.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(
        language: String,
        page: Int,
        homeCategory: HomeCategory
    ): Flow<List<Movie>?>

    suspend fun getAllMovies(
        language: String,
        page: Int
    ): Flow<List<Movie>>

    suspend fun getMovieForId(movieId: Int, language: String): Flow<Movie?>
}