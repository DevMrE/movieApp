package com.kmp.movieapp.features.trending.data.repository

import com.kmp.movieapp.core.network.util.mapOnSuccess
import com.kmp.movieapp.features.trending.data.mapper.toTrending
import com.kmp.movieapp.features.trending.data.service.api.TrendingApiService
import com.kmp.movieapp.features.trending.domain.model.Trending
import com.kmp.movieapp.features.trending.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TrendingRepositoryImpl(
    private val trendingApiService: TrendingApiService
) : TrendingRepository {
    override suspend fun getAll(): Flow<List<Trending>> = flow {
        trendingApiService.fetchAll()
            .mapOnSuccess { dto ->
                emit(dto.results?.map { it.toTrending() } ?: emptyList())
            }
    }
}