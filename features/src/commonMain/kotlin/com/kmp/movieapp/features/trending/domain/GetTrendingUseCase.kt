package com.kmp.movieapp.features.trending.domain

import com.kmp.movieapp.features.trending.domain.model.Trending
import com.kmp.movieapp.features.trending.domain.model.TrendingType
import com.kmp.movieapp.features.trending.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow

class GetTrendingUseCase(
    private val repository: TrendingRepository
) {
    suspend operator fun invoke(
        trendingType: TrendingType
    ): Flow<List<Trending>> {
        return when (trendingType) {
            TrendingType.ALL -> repository.getAll()
            else -> repository.getAll()
        }
    }
}