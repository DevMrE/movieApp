package com.kmp.movieapp.discover.domain.usecase

import com.kmp.movieapp.discover.domain.model.Filter
import com.kmp.movieapp.discover.domain.repository.DiscoverRepository
import kotlinx.coroutines.flow.combine

class GetDiscoverUseCase(
    private val discoverRepository: DiscoverRepository
) {

    suspend operator fun invoke(page: Int, filter: Filter? = null) = combine(
        discoverRepository.getDiscoverMovies(page, filter),
        discoverRepository.getDiscoverSeries(page)
    ) { movies, series ->
        val movieList = movies.discoverContent ?: emptyList()
        val seriesList = series.discoverContent ?: emptyList()
        (movieList + seriesList).distinctBy { it.title }
    }
}