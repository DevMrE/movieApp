package com.kmp.movieapp.search.domain.usecase

import com.kmp.movieapp.search.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {

    suspend operator fun invoke(query: String): Flow<List<Search>>

}