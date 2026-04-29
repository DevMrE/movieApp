package com.kmp.movieapp.movie.data.domain.usecase

import com.kmp.movieapp.movie.data.domain.repository.MovieRepository


class LoadNextMoviesForCategoryUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(page: Int) = movieRepository
        .getPopularMovies(
            page = page,
        )
}