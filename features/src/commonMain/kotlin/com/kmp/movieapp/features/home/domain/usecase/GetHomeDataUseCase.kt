package com.kmp.movieapp.features.home.domain.usecase

import com.kmp.movieapp.core.util.tuples.with
import com.kmp.movieapp.features.trending.domain.GetTrendingUseCase
import com.kmp.movieapp.features.trending.domain.model.TrendingType
import kotlinx.coroutines.flow.combine

class GetHomeDataUseCase(
    private val getTrendingUseCase: GetTrendingUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) {

    suspend operator fun invoke() = combine(
        getTrendingUseCase(TrendingType.ALL),
        getPopularMoviesUseCase()
    ) { trending, popularMovies ->
        trending with popularMovies
    }
}