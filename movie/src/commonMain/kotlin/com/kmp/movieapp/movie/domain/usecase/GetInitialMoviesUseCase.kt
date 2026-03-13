package com.kmp.movieapp.movie.domain.usecase

import com.kmp.movieapp.core.util.tuples.with
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.combine

internal class GetInitialMoviesUseCase(
    private val movieRepository: MovieRepository
) {

    operator fun invoke() = combine(
        movieRepository.getMovies(language = "", page = 1, movieCategory = MovieCategory.POPULAR),
        movieRepository.getMovies(language = "", page = 1, movieCategory = MovieCategory.TOP_RATED),
        movieRepository.getMovies(language = "", page = 1, movieCategory = MovieCategory.NOW_PLAYING)
    ) { popular, topRated, nowPlaying ->
        popular with topRated with nowPlaying
    }
}