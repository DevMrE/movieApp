package com.kmp.movieapp.movie.domain.usecase

import com.kmp.movieapp.movie.domain.repository.MovieRepository


class LoadMediaListForCategoryUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(page: Int) = movieRepository
        .getPopularMovies(
            page = page,
        )
}