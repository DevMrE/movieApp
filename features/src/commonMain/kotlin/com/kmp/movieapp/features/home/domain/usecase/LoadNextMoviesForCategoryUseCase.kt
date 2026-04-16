package com.kmp.movieapp.features.home.domain.usecase

import com.kmp.movieapp.features.home.domain.model.MovieCategory
import com.kmp.movieapp.features.home.domain.repository.MovieRepository

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