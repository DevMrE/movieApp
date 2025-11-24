package com.kmp.movieapp.movie.domain.repository

import com.kmp.movieapp.movie.domain.model.Movie

interface MovieRepository {

    suspend fun getMovies(): List<Movie>
}