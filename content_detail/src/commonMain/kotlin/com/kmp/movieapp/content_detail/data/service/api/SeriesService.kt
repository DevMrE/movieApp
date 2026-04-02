package com.kmp.movieapp.content_detail.data.service.api

import com.kmp.movieapp.content_detail.data.model.api.response.ContentDetailDto
import com.kmp.movieapp.core.network.util.Result

internal interface SeriesService {
    suspend fun fetchSeriesDetail(seriesId: Int): Result<ContentDetailDto, Unit>
}