package com.kmp.movieapp.movie.data.repository

import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.network.util.mapOnFailure
import com.kmp.movieapp.core.network.util.mapOnSuccess
import com.kmp.movieapp.core.network.util.onError
import com.kmp.movieapp.core.network.util.onFailure
import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.movie.data.domain.repository.MovieRepository
import com.kmp.movieapp.movie.data.mapper.toMovie
import com.kmp.movieapp.movie.data.service.MovieApiService
import com.kmp.movieapp.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,

) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Flow<List<Movie>?> = flow {
        movieApiService.fetchMoviesPopularMovies(page = page)
            .mapOnSuccess { data ->
                data.results?.map { movieDto ->
                    movieDto.toMovie()
                } ?: emptyList()
            }
            .mapOnFailure {
                // Do an DB call and get the data from there.
                Result.Success(emptyList())
            }
            .onSuccess { movieList ->
                emit(movieList)
            }
            .onError {
                logE<MovieRepository>(message = "Error: $it")
            }.onFailure {
                // Placeholder for loading movies from a database.
                emit(null)
            }
    }

    override suspend fun getAllMovies(page: Int): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieForId(movieId: Int): Flow<Movie?> = flow {
        movieApiService.findMovieForId(movieId)
            .onSuccess {
                emit(it.toMovie())
            }.onFailure {
                emit(null)
            }.onError {
                logE<MovieRepository>(message = "Error: $it")
            }
    }
}