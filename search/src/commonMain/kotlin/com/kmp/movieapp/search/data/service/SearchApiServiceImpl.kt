package com.kmp.movieapp.search.data.service

import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.search.data.model.api.request.SearchRequestDto
import com.kmp.movieapp.search.data.model.api.response.SearchDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.http.HttpStatusCode

internal class SearchApiServiceImpl(
    private val httpClient: HttpClient
) : SearchApiService {

    override suspend fun fetchSearch(query: String): Result<ApiResponseDto<SearchDto>, ApiError> =
        try {
            val response = httpClient.get(
                resource = SearchRequestDto(query)
            )

            if (response.status == HttpStatusCode.OK) {
                Result.Success(response.body())
            } else Result.Failure(value = ApiError.NotFound)

        } catch (e: Exception) {
            Logger.e(messageString = e.message.toString())

            Result.Failure(e.message)
        }
}