package com.kmp.movieapp.movie.data.repository

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.util.onCompletion
import com.kmp.movieapp.core.network.util.onError
import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.genre.domain.repository.GenreRepository
import com.kmp.movieapp.movie.data.mapper.toMovie
import com.kmp.movieapp.movie.data.service.MovieApiService
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val genreRepository: GenreRepository
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Flow<List<Movie>> = flow {
        movieApiService.fetchPopularMovies(page = page)
            .onSuccess { movieDtoList ->
                movieDtoList.map { movieDto ->
                    movieDto.toMovie(genreRepository.movieGenres.value)
                }
            }
            .onError { error ->
                when (error.value) {
                    // TODO: Do an DB call and get the data from there.
                    is ApiError.NetworkError,
                    is ApiError.Unknown -> emptyList()

                    else -> emptyList()
                }
            }
            .onCompletion { contentList ->
                emit(contentList)
            }
    }

    override suspend fun getMovieForId(movieId: Int): Flow<Movie?> = flow {
        movieApiService.findMovieForId(movieId)
            .onSuccess { movieDto ->
                movieDto.toMovie()
            }
            .onError { error ->
                when (error.value) {
                    // TODO: Do an DB call and get the data from there if we have an NetworkError.
                    is ApiError.NetworkError,
                    is ApiError.Unknown -> null

                    else -> null
                }
            }
            .onCompletion { data ->
                emit(data)
            }
    }

    override suspend fun getDiscoveredMovies(page: Int): Flow<List<Movie>> = flow {
        movieApiService.fetchDiscoveredMovies(page = page)
            .onSuccess { movieResponseList ->
                movieResponseList.map { movieDto ->
                    movieDto.toMovie()
                }
            }
            .onError { error ->
                when (error.value) {
                    // TODO: Do an DB call and get the data from there.
                    is ApiError.NetworkError,
                    is ApiError.Unknown -> emptyList()

                    else -> emptyList()
                }
            }
            .onCompletion { contentList ->
                emit(contentList)
            }
    }
}