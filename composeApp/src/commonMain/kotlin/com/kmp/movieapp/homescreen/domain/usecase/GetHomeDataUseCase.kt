package com.kmp.movieapp.homescreen.domain.usecase

import com.kmp.movieapp.core.util.tuples.with
import com.kmp.movieapp.movie.domain.usecase.GetPopularMoviesUseCase
import com.kmp.movieapp.series.domain.usecase.GetPopularSeriesUseCase
import com.kmp.movieapp.trending.domain.GetTrendingUseCase
import com.kmp.movieapp.trending.domain.model.TrendingType
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