package com.kmp.movieapp.search.domain.usecase

import com.kmp.movieapp.movie.domain.repository.MovieRepository
import com.kmp.movieapp.search.data.mapper.toFilteredMovies
import com.kmp.movieapp.search.data.mapper.toFilteredSeries
import com.kmp.movieapp.search.domain.model.FilterType
import com.kmp.movieapp.series.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.combine

class GetSearchedMediaUseCase(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) {

    suspend operator fun invoke(page: Int, filterType: FilterType? = null) = combine(
        movieRepository.getDiscoveredMovies(page),
        seriesRepository.getPopularSeries(page)
    ) { movies, series ->
        val movieList = movies.map { movie -> movie.toFilteredMovies() }
        val seriesList = series.map { series -> series.toFilteredSeries() }

        buildList {
            addAll(movieList)
            addAll(seriesList)
        }.distinctBy { it.title }
    }
}