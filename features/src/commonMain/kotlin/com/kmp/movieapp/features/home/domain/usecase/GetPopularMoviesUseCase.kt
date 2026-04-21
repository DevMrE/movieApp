package com.kmp.movieapp.features.home.domain.usecase

import com.kmp.movieapp.features.home.domain.model.HomeCategory
import com.kmp.movieapp.features.home.domain.repository.MovieRepository

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke() =
        movieRepository.getPopularMovies(language = "", page = 1, homeCategory = HomeCategory.POPULAR_MOVIES)

}