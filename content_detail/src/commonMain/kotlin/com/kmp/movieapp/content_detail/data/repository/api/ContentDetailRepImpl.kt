package com.kmp.movieapp.content_detail.data.repository.api

import com.kmp.movieapp.content_detail.data.mapper.toContentDetail
import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.core.network.util.onFailure
import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.movie.data.service.MovieService
import com.kmp.series.data.service.SeriesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ContentDetailRepImpl(
    private val movieService: MovieService,
    private val seriesService: SeriesService
) : ContentDetailRepository {

    override suspend fun getMovieDetail(contentId: String): Flow<ContentDetail> = flow {
        movieService.findMovieForId(movieId = contentId.toInt(), language = "de")
            .onSuccess {
                emit(it.toContentDetail())
            }
    }

    override suspend fun getSeriesDetail(contentId: String): Flow<ContentDetail> = flow {
        seriesService.findSeriesForId(seriesId = contentId.toInt(), language = "de")
            .onSuccess {
                emit(it.toContentDetail())
            }.onFailure {
                logE<ContentDetailRepository>(message = "Failure on getSeriesDetail: ${it.message}")
            }
    }
}