package com.kmp.movieapp.browse.domain.usecase

import com.kmp.movieapp.browse.data.mapper.toDiscoverMovies
import com.kmp.movieapp.browse.data.mapper.toDiscoverSeries
import com.kmp.movieapp.browse.domain.model.discover.Filter
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import com.kmp.movieapp.series.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.combine

class GetDiscoverUseCase(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) {

    suspend operator fun invoke(page: Int, filter: Filter? = null) = combine(
        movieRepository.getDiscoveredMovies(page),
        seriesRepository.getPopularSeries(page)
    ) { movies, series ->
        val movieList = movies.map { movie -> movie.toDiscoverMovies() }
        val seriesList = series.map { series -> series.toDiscoverSeries() }

        buildList {
            addAll(movieList)
            addAll(seriesList)
        }.distinctBy { it.title }
    }
}