package com.kmp.movieapp.movie.domain.repository

import com.kmp.movieapp.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(page: Int = 1): Flow<List<Movie>>

    suspend fun getMovieForId(movieId: Int): Flow<Movie?>

    suspend fun getDiscoveredMovies(page: Int): Flow<List<Movie>>
}