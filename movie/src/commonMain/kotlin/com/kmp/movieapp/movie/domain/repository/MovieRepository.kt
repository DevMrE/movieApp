package com.kmp.movieapp.movie.domain.repository

import com.kmp.movieapp.core.domain.repository.Repository
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieCategory
import kotlinx.coroutines.flow.Flow

interface MovieRepository: Repository {

     suspend fun getMovies(language: String, movieCategory: MovieCategory): Flow<List<Movie>>
}