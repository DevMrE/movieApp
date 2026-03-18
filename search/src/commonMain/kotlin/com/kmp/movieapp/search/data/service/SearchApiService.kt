package com.kmp.movieapp.search.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.model.ApiResponseDto
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.search.data.model.api.response.SearchDto

/**
 * Defines the contract for performing search requests against the remote API.
 *
 * Implementations are responsible for executing the search query and returning
 * the result in a [Result] wrapper that encapsulates either the successful
 * [ApiResponseDto] containing [SearchDto] items or an [ApiError] in case of failure.
 *
 * Usage example:
 * ```
 * val result = searchApiService.fetchSearch("Inception")
 * result.alsoOnSuccess {
 *    handleSuccess(result.data)
 * }.alsoOnFailure {
 *    handleError(result.error)
 * }
 * ```
 */
internal interface SearchApiService {

    /**
     * Performs a search request for the given [query] string.
     *
     * @param query The search term to query the API with.
     * @return A [Result] containing either:
     *  - [ApiResponseDto] of [SearchDto] on success
     *  - [ApiError] on failure
     */
    suspend fun fetchSearch(query: String): Result<ApiResponseDto<SearchDto>, ApiError>
}