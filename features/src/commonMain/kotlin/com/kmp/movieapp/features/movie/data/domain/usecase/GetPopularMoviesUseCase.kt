package com.kmp.movieapp.features.movie.data.domain.usecase

import com.kmp.movieapp.features.movie.data.domain.repository.MovieRepository

internal class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke(page: Int = 1) =
        movieRepository.getPopularMovies(page = page)

}