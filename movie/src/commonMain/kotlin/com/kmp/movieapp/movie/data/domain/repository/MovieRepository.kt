package com.kmp.movieapp.movie.data.domain.repository

import com.kmp.movieapp.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(
        page: Int,
    ): Flow<List<Movie>?>

    suspend fun getAllMovies(
        page: Int
    ): Flow<List<Movie>>

    suspend fun getMovieForId(movieId: Int): Flow<Movie?>
}