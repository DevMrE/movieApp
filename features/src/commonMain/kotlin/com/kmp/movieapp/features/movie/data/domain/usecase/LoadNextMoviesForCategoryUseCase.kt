package com.kmp.movieapp.features.movie.data.domain.usecase

import com.kmp.movieapp.features.movie.data.domain.model.HomeCategory
import com.kmp.movieapp.features.movie.data.domain.repository.MovieRepository

internal class LoadNextMoviesForCategoryUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(page: Int, homeCategory: HomeCategory) = movieRepository
        .getPopularMovies(
            language = "de",
            page = page,
            homeCategory = homeCategory
        )
}