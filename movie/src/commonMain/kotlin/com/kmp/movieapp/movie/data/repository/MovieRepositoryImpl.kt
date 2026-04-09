package com.kmp.movieapp.movie.data.repository

import com.kmp.movieapp.core.network.util.onError
import com.kmp.movieapp.core.network.util.onFailure
import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.core.util.logger.logE
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

internal class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

    private val _movieLists = MutableStateFlow<Map<MovieCategory, List<Movie>>>(emptyMap())

    override suspend fun getMovies(
        language: String,
        page: Int,
        movieCategory: MovieCategory
    ): Flow<List<Movie>?> = flow {
        movieService.fetchMoviesForCategory(
            language = language,
            page = page,
            movieListCategory = movieCategory.toMovieListCategory()
        ).onSuccess { data ->
            val movieList = data.results?.map { movieDto ->
                movieDto.toMovie()
            } ?: emptyList()

            _movieLists.update { currentMap ->
                val existingMovies = currentMap[movieCategory] ?: emptyList()
                val updatedMovies = (existingMovies + movieList).distinct()
                currentMap + (movieCategory to updatedMovies)
            }

            emit(_movieLists.value[movieCategory] ?: emptyList())
        }.onError {
            logE<MovieRepository>(message = "Error: $it")
        }.onFailure {
            // Placeholder for loading movies from a database.
            emit(null)
        }
    }

    override suspend fun getAllMovies(language: String, page: Int): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }
}