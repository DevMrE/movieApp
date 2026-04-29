package com.kmp.movieapp.movie.data.domain.usecase

import com.kmp.movieapp.movie.data.domain.repository.MovieRepository

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke(page: Int = 1) =
        movieRepository.getPopularMovies(page = page)

}