package com.kmp.movieapp.movie.domain.usecase

import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.domain.repository.MovieRepository

internal class LoadNextMoviesForCategoryUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(page: Int, movieCategory: MovieCategory) = movieRepository
        .getMovies(
            language = "de",
            page = page,
            movieCategory = movieCategory
        )
}