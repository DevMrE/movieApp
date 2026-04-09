package com.kmp.movieapp.movie.domain.repository

import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieCategory
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(
        language: String,
        page: Int,
        movieCategory: MovieCategory
    ): Flow<List<Movie>?>

    suspend fun getAllMovies(
        language: String,
        page: Int
    ): Flow<List<Movie>>

    suspend fun getMovieForId(movieId: Int, language: String): Flow<Movie?>
}