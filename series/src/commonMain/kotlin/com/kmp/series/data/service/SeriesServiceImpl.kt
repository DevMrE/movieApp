package com.kmp.series.data.service

import com.kmp.movieapp.core.network.http.HandleHttpStatus
import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.core.util.try_catch.multiCatch
import com.kmp.series.data.model.request.SeriesRequestDto
import com.kmp.series.data.model.response.SeriesDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.resources.get

internal class SeriesServiceImpl(
    private val httpClient: HttpClient
) : SeriesService {
    override suspend fun findSeriesForId(
        seriesId: Int,
        language: String
    ): Result<SeriesDto, ApiError> =
        multiCatch(
            tryBlock = {
                val response = httpClient.get(
                    resource = SeriesRequestDto(seriesId = seriesId, language = language)
                )

                val dto = response.body<SeriesDto>()
                logI("SeriesDto: $dto")
                Result.Success(response.body())
            },
            handlers = mapOf(
                listOf(ClientRequestException::class, ServerResponseException::class) to { e ->
                    val ex = e as ResponseException
                    HandleHttpStatus.getResultForStatus(ex.response.status)
                },
                listOf(Exception::class) to { e ->
                    logE<SeriesService>(message  = "Error during findSeriesForId: $seriesId, message: ${e.message}")
                    HandleHttpStatus.getResultForStatus(null)
                }
            )
        )
}
