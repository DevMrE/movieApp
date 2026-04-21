package com.kmp.movieapp.features.trending.domain.repository

import com.kmp.movieapp.features.trending.domain.model.Trending
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {

    suspend fun getAll(): Flow<List<Trending>>
}