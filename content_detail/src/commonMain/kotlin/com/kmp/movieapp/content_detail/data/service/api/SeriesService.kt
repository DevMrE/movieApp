package com.kmp.movieapp.content_detail.data.service.api

import com.kmp.movieapp.content_detail.data.model.api.response.ContentDetailDto
import com.kmp.movieapp.core.util.network.Result

internal interface SeriesService {
    suspend fun fetchSeriesDetail(movieId: String): Result<ContentDetailDto, Unit>
}