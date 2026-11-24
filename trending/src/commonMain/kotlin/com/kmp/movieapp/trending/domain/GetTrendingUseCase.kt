package com.kmp.movieapp.trending.domain

import com.kmp.movieapp.trending.domain.model.Trending
import com.kmp.movieapp.trending.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow

class GetTrendingUseCase(
    private val repository: TrendingRepository
) {
    suspend operator fun invoke(): Flow<List<Trending>> = repository.getAll()
}