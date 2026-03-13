package com.kmp.movieapp.search.data.service

import com.kmp.movieapp.core.data.model.ApiError
import com.kmp.movieapp.core.data.model.ApiResponseDto
import com.kmp.movieapp.core.util.network.Result
import com.kmp.movieapp.search.data.model.api.response.SearchDto

interface SearchApiService {

    suspend fun fetchSearch(query: String): Result<ApiResponseDto<SearchDto>, ApiError>
}