package com.kmp.movieapp.features.home.domain.usecase

import com.kmp.movieapp.features.home.domain.model.HomeCategory
import com.kmp.movieapp.features.home.domain.repository.MovieRepository

internal class LoadNextMoviesForCategoryUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(page: Int, homeCategory: HomeCategory) = movieRepository
        .getMovies(
            language = "de",
            page = page,
            homeCategory = homeCategory
        )
}