package com.kmp.movieapp.movie.data.repository

import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.core.util.network.alsoOnFailure
import com.kmp.movieapp.core.util.network.alsoOnSuccess
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.updateAndGet

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

    private val _movieList: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    val genresState: StateFlow<List<MovieGenre>> = flow {
        movieService.fetchMovieGenres("de")
            .alsoOnSuccess { dto ->
                val genreList = dto?.genres?.map { it.toMovieGenre() } ?: emptyList()
                emit(genreList)
            }
            .alsoOnFailure {
                Logger.e("Error: ${it.value}")
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
        ).alsoOnSuccess { data ->
            val movieList = data.results?.mapNotNull { movieDto ->
                movieDto?.toMovie()?.copy(
                    genres = genresState.value.filter { movieDto.genreIds?.contains(it.id).isTrue }
                )
            } ?: emptyList()

            val updatedList = _movieList.updateAndGet { it + movieList }.distinct()
            emit(updatedList)
        }.alsoOnFailure {
            Logger.e("Error: ${it.value}")
        }
    }

    override suspend fun getAllMovies(language: String, page: Int): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }
}