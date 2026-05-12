package com.kmp.movieapp.discover.domain.usecase

import com.kmp.movieapp.discover.domain.repository.DiscoverRepository
import kotlinx.coroutines.flow.combine

class GetDiscoverUseCase(
    private val discoverRepository: DiscoverRepository
) {

    suspend operator fun invoke(page: Int) = combine(
        discoverRepository.getDiscoverMovies(page),
        discoverRepository.getDiscoverSeries(page)
    ) { movies, series ->
        (movies + series).distinctBy { it.title }
    }
}