package com.kmp.movieapp.features.home.domain.usecase

import com.kmp.movieapp.core.util.tuples.with
import com.kmp.movieapp.features.series.domain.usecase.GetPopularSeriesUseCase
import com.kmp.movieapp.features.trending.domain.GetTrendingUseCase
import com.kmp.movieapp.features.trending.domain.model.TrendingType
import com.kmp.movieapp.movie.data.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.combine

internal class GetHomeDataUseCase(
    private val getTrendingUseCase: GetTrendingUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularSeriesUseCase: GetPopularSeriesUseCase
) {

    suspend operator fun invoke() = combine(
        getTrendingUseCase(TrendingType.ALL),
        getPopularMoviesUseCase(),
        getPopularSeriesUseCase()
    ) { trending, popularMovies, popularSeries ->
        trending with popularMovies with popularSeries
    }
}