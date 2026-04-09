package com.kmp.movieapp.content_detail.data.service.api

import co.touchlab.kermit.Logger
import com.kmp.movieapp.content_detail.data.model.api.request.MovieDetailRequestDto
import com.kmp.movieapp.content_detail.data.model.api.response.ContentDetailDto
import com.kmp.movieapp.core.network.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.http.HttpStatusCode

internal class MovieServiceImpl(
    private val httpClient: HttpClient
) : MovieService {

    override suspend fun fetchMovieDetail(movieId: Int): Result<ContentDetailDto, Unit> {
        return try {
            val response = httpClient.get(
                resource = MovieDetailRequestDto(
                    movieId = movieId,
                )
            )

            if (response.status == HttpStatusCode.OK && response.body<ContentDetailDto?>() != null) {
                Result.Success(response.body<ContentDetailDto>())
            } else Result.Failure("call was successful but data is null")
        } catch (e: Exception) {
            Logger.e(tag = "ApiCall", messageString = "${e.message}")
            Result.Failure("Something went wrong during api call", e)
        }
    }
}