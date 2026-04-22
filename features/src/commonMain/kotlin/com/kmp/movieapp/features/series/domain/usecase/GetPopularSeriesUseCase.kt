package com.kmp.movieapp.features.series.domain.usecase

import com.kmp.movieapp.features.series.domain.repository.SeriesRepository

internal class GetPopularSeriesUseCase(
    private val repository: SeriesRepository
) {
    suspend operator fun invoke() = repository.getPopularSeries()
}