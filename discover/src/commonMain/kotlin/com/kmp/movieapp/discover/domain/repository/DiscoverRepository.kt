package com.kmp.movieapp.discover.domain.repository

import com.kmp.movieapp.discover.domain.model.Discover
import kotlinx.coroutines.flow.Flow

interface DiscoverRepository {

    suspend fun getDiscoverMovies(page: Int): Flow<List<Discover>>

    suspend fun getDiscoverSeries(page: Int): Flow<List<Discover>>
}