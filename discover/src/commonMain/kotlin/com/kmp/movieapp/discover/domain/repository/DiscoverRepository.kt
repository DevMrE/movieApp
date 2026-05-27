package com.kmp.movieapp.discover.domain.repository

import com.kmp.movieapp.discover.domain.model.Discover
import com.kmp.movieapp.discover.domain.model.Filter
import kotlinx.coroutines.flow.Flow

interface DiscoverRepository {

    suspend fun getDiscoverMovies(page: Int, filter: Filter? = null): Flow<List<Discover>>

    suspend fun getDiscoverSeries(page: Int, filter: Filter? = null): Flow<List<Discover>>
}