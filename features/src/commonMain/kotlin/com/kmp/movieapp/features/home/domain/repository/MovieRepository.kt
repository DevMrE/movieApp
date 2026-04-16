package com.kmp.movieapp.features.home.domain.repository

import com.kmp.movieapp.features.home.domain.model.HomeCategory
import com.kmp.movieapp.features.home.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(
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