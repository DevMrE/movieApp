package com.kmp.movieapp.movie.data.repository

import co.touchlab.kermit.Logger
import com.kmp.movieapp.movie.data.model.mapper.toMovie
import com.kmp.movieapp.movie.data.model.mapper.toMovieGenre
import com.kmp.movieapp.movie.data.model.mapper.toMovieListCategory
import com.kmp.movieapp.movie.data.service.MovieService
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.domain.model.MovieGenre
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

    private val _movieList: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    val genresState: StateFlow<List<MovieGenre>> = flow {
        movieService.fetchMovieGenres("de")
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

    override suspend fun getMovies(
        language: String,
        page: Int,
        movieCategory: MovieCategory
    ): Flow<List<Movie>> = flow {
        movieService.fetchMoviesForCategory(
            language = language,
            page = page,
            movieListCategory = movieCategory.toMovieListCategory()
        ).onSuccess { data ->
            val movieList = data.movieList?.mapNotNull { movieDto ->
                movieDto?.toMovie()?.copy(
                    genres = genresState.value.filter { movieDto.genreIds?.contains(it.id) == true }
                )
            } ?: emptyList()

            val updatedList = _movieList.updateAndGet { it + movieList }.distinct()
            emit(updatedList)
        }.onFailure { error ->
            error.message?.let { message ->
                Logger.e(throwable = error, tag = "Movies", messageString = message)
            }
        }
    }
}