package com.kmp.movieapp.trending.data.repository

import com.kmp.movieapp.core.network.util.mapOnSuccess
import com.kmp.movieapp.trending.data.mapper.toTrending
import com.kmp.movieapp.trending.data.service.TrendingApiService
import com.kmp.movieapp.trending.domain.model.Trending
import com.kmp.movieapp.trending.domain.repository.TrendingRepository
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