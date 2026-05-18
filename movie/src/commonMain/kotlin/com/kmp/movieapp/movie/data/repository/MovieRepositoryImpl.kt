package com.kmp.movieapp.movie.data.repository

import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.network.util.mapOnError
import com.kmp.movieapp.core.network.util.mapOnSuccess
import com.kmp.movieapp.core.network.util.onError
import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.movie.data.mapper.toMovie
import com.kmp.movieapp.movie.data.service.MovieService
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Flow<List<Movie>> = flow {
        movieService.fetchMoviesPopularMovies(page = page)
            .mapOnSuccess { data ->
                data.results?.map { movieDto ->
                    movieDto.toMovie()
                } ?: emptyList()
            }
            .mapOnError {
                // Do an DB call and get the data from there.
                Result.Success(emptyList())
            }
            .onSuccess { movieList ->
                emit(movieList)
            }
            .onError {
                logE<MovieRepository>(message = "Error: $it")
            }
    }

    override suspend fun getMovieForId(movieId: Int): Flow<Movie?> = flow {
        movieService.findMovieForId(movieId)
            .onSuccess {
                emit(it.toMovie())
            }.onError {
                logE<MovieRepository>(message = "Error: $it")
                emit(null)
            }
    }
}