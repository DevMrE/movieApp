package com.kmp.movieapp.movie.data.repository

import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.util.network.alsoOnFailure
import com.kmp.movieapp.core.util.network.alsoOnSuccess
import com.kmp.movieapp.movie.data.model.mapper.toMovie
import com.kmp.movieapp.movie.data.model.mapper.toMovieListCategory
import com.kmp.movieapp.movie.data.service.MovieService
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

    private val _movieLists = MutableStateFlow<Map<MovieCategory, List<Movie>>>(emptyMap())

    override fun getMovies(
        language: String,
        page: Int,
        movieCategory: MovieCategory
    ): Flow<List<Movie>> = flow {
        movieService.fetchMoviesForCategory(
            language = language,
            page = page,
            movieListCategory = movieCategory.toMovieListCategory()
        ).alsoOnSuccess { data ->
            val movieList = data.results?.mapNotNull { movieDto ->
                movieDto?.toMovie()
            } ?: emptyList()

            _movieLists.update { currentMap ->
                val existingMovies = currentMap[movieCategory] ?: emptyList()
                val updatedMovies = (existingMovies + movieList).distinct()
                currentMap + (movieCategory to updatedMovies)
            }

            emit(_movieLists.value[movieCategory] ?: emptyList())
        }.alsoOnFailure {
            Logger.e("Error: ${it.value}")
        }
    }

    override fun getAllMovies(language: String, page: Int): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }
}