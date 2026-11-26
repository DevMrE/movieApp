package com.kmp.movieapp.trending.data.repository

import com.kmp.movieapp.core.network.util.onCompletion
import com.kmp.movieapp.core.network.util.onSuccess
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
            .onSuccess { dto ->
                dto.results?.map { it.toTrending() } ?: emptyList()
            }
            .onCompletion { contentList ->
                emit(contentList)
            }
    }
}