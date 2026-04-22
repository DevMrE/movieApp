package com.kmp.movieapp.features.movie.data.domain.usecase

import com.kmp.movieapp.features.home.presentation.model.HomeCategory
import com.kmp.movieapp.features.movie.data.domain.repository.MovieRepository

internal class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke() =
        movieRepository.getPopularMovies(language = "", page = 1, homeCategory = HomeCategory.POPULAR_MOVIES)

}