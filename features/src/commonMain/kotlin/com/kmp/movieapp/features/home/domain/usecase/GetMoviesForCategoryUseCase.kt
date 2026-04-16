package com.kmp.movieapp.features.home.domain.usecase

import com.kmp.movieapp.core.util.tuples.with
import com.kmp.movieapp.features.home.domain.model.HomeCategory
import com.kmp.movieapp.features.home.domain.repository.MovieRepository
import kotlinx.coroutines.flow.combine

internal class GetMoviesForCategoryUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke() = combine(
        movieRepository.getMovies(language = "", page = 1, homeCategory = HomeCategory.POPULAR),
        movieRepository.getMovies(language = "", page = 1, homeCategory = HomeCategory.TOP_RATED),
        movieRepository.getMovies(
            language = "",
            page = 1,
            homeCategory = HomeCategory.NOW_PLAYING
        )
    ) { popular, topRated, nowPlaying ->
        popular with topRated with nowPlaying
    }
}