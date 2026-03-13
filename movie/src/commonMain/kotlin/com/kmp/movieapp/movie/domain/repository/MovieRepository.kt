package com.kmp.movieapp.movie.domain.repository

import com.kmp.movieapp.core.domain.repository.Repository
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieCategory
import kotlinx.coroutines.flow.Flow

internal interface MovieRepository : Repository {

    fun getMovies(
        language: String,
        page: Int,
        movieCategory: MovieCategory
    ): Flow<List<Movie>>

    fun getAllMovies(
        language: String,
        page: Int
    ): Flow<List<Movie>>
}