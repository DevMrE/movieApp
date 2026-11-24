package com.kmp.movieapp.discover.domain

import com.kmp.movieapp.core.util.tuples.Triple
import com.kmp.movieapp.core.util.tuples.with
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import com.kmp.movieapp.series.domain.repository.SeriesRepository
import com.kmp.movieapp.trending.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.combine

/**
 * Returns an anonym [Triple] class with the list of
 * trending movies and series, popular movies and popular series.
 */
internal class GetDiscoverDataUseCase(
    private val trendingRepository: TrendingRepository,
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) {

    suspend operator fun invoke() = combine(
        trendingRepository.getAll(),
        movieRepository.getPopularMovies(),
        seriesRepository.getPopularSeries()
    ) { trending, popularMovies, popularSeries ->
        trending with popularMovies with popularSeries
    }
}