package com.kmp.movieapp.series.domain.usecase

import com.kmp.movieapp.series.domain.repository.SeriesRepository

class GetPopularSeriesUseCase(
    private val repository: SeriesRepository
) {
    suspend operator fun invoke(page: Int = 1) = repository.getPopularSeries()
}