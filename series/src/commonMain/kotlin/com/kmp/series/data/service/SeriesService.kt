package com.kmp.series.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.util.Result
import com.kmp.series.data.model.response.SeriesDto

internal interface SeriesService {

    suspend fun findSeriesForId(seriesId: Int, language: String): Result<SeriesDto, ApiError>
}