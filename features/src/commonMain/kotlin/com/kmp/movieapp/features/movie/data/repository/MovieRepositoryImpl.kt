package com.kmp.movieapp.features.movie.data.repository

import com.kmp.movieapp.core.network.util.onError
import com.kmp.movieapp.core.network.util.onFailure
import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.features.movie.data.service.MovieApiService
import com.kmp.movieapp.features.movie.data.domain.model.HomeCategory
import com.kmp.movieapp.features.movie.data.domain.repository.MovieRepository
import com.kmp.movieapp.features.movie.data.mapper.toMovie
import com.kmp.movieapp.features.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

internal class MovieRepositoryImpl(
    private val movieApiService: MovieApiService
) : MovieRepository {

    private val _movieLists = MutableStateFlow<Map<HomeCategory, List<Movie>>>(emptyMap())

    override suspend fun getPopularMovies(
        language: String,
        page: Int,
        homeCategory: HomeCategory
    ): Flow<List<Movie>?> = flow {
        movieApiService.fetchMoviesPopularMovies(
            page = page
        ).onSuccess { data ->
            val movieList = data.results?.map { movieDto ->
                movieDto.toMovie()
            } ?: emptyList()

            _movieLists.update { currentMap ->
                val existingMovies = currentMap[homeCategory] ?: emptyList()
                val updatedMovies = (existingMovies + movieList).distinct()
                currentMap + (homeCategory to updatedMovies)
            }

            emit(_movieLists.value[homeCategory] ?: emptyList())
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

    override suspend fun getMovieForId(movieId: Int, language: String): Flow<Movie?> = flow {
        movieApiService.findMovieForId(movieId, language = language).onSuccess {
            emit(it.toMovie())
        }.onFailure {
            emit(null)
        }.onError {
            logE<MovieRepository>(message = "Error: $it")
        }
    }
}