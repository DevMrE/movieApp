package com.kmp.movieapp.movie.data.repository

import co.touchlab.kermit.Logger
import com.kmp.movieapp.movie.data.model.mapper.toMovie
import com.kmp.movieapp.movie.data.model.mapper.toMovieGenre
import com.kmp.movieapp.movie.data.service.MovieService
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieGenre
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

    val genresState: StateFlow<List<MovieGenre>> = flow {
        movieService.getMovieGenres("de")
            .onSuccess { dto ->
                val genreList = dto.genres?.map {
                    it.toMovieGenre()
                } ?: emptyList()
                emit(genreList)
            }
            .onFailure {
                Logger.e(messageString = it.message ?: "", throwable = it, tag = "Movie")
            }

    }.stateIn(
        scope = CoroutineScope(Dispatchers.Default),
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

    override suspend fun getMovies(): List<Movie> {
        return movieService.getPopularMovies("de", 1)
            ?.movieList?.mapNotNull { movieDto ->
                movieDto?.toMovie()?.copy(
                    genres = genresState.value.filter { movieDto.genreIds?.contains(it.id) == true  }
                )
            } ?: emptyList()
    }
}