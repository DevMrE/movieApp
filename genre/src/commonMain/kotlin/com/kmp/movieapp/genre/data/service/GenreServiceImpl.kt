package com.kmp.movieapp.genre.data.service

import com.kmp.movieapp.core.network.http.HandleError
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.boolean.isFalse
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.core.util.try_catch.handler
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.movieapp.genre.data.model.request.GenreRequestDto
import com.kmp.movieapp.genre.data.model.response.GenreDto
import com.kmp.movieapp.genre.data.model.response.GenreResponseDto
import com.kmp.movieapp.genre.domain.model.GenreContentType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.resources.get

internal class GenreServiceImpl(
    private val httpClient: HttpClient
) : GenreService {

    override suspend fun fetchGenres(type: GenreContentType): Result<List<GenreDto>, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(resource = GenreRequestDto(pathType = type))
                val genres = response.body<GenreResponseDto>().genres
                if (genres.isEmpty().isFalse) {
                    Result.Success(response.body<GenreResponseDto>().genres)
                } else Result.Failure(message = "Something went wrong, response was successful but response is empty.")
            },
            handler<ResponseException, Result<List<GenreDto>, ApiError>> { e ->
                HandleError.getResultForHttpStatus(e.response.status)
            },
            handler<Exception, Result<List<GenreDto>, ApiError>> { e ->
                logE<GenreService>(message = "Error during fetchGenres: ${e.message}")
                HandleError.getResultForHttpStatus(null)
            }
        )
}