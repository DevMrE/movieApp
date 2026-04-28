package com.kmp.movieapp.features.movie.data.domain.usecase

import com.kmp.movieapp.features.home.presentation.model.HomeCategory
import com.kmp.movieapp.features.movie.data.domain.repository.MovieRepository
import com.kmp.movieapp.features.series.domain.repository.SeriesRepository
import com.kmp.movieapp.features.trending.domain.repository.TrendingRepository

internal class LoadNextMoviesForCategoryUseCase(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    private val trendingRepository: TrendingRepository
) {

    suspend operator fun invoke(page: Int, homeCategory: HomeCategory) = movieRepository
        .getPopularMovies(
            page = page,
        )
}