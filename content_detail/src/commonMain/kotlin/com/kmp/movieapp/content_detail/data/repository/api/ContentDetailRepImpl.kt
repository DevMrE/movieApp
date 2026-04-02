package com.kmp.movieapp.content_detail.data.repository.api

import com.kmp.movieapp.content_detail.data.mapper.toContentDetail
import com.kmp.movieapp.content_detail.data.service.api.MovieService
import com.kmp.movieapp.content_detail.data.service.api.SeriesService
import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.core.network.util.mapOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ContentDetailRepImpl(
    private val movieService: MovieService,
    private val seriesService: SeriesService
) : ContentDetailRepository {

    override suspend fun getMovieDetail(contentId: String): Flow<ContentDetail> = flow {
        movieService.fetchMovieDetail(movieId = contentId.toInt())
            .mapOnSuccess {
                emit(it.toContentDetail())
            }
    }

    override suspend fun getSeriesDetail(contentId: String): Flow<ContentDetail> = flow {
        seriesService.fetchSeriesDetail(contentId.toInt())
            .mapOnSuccess {
                emit(it.toContentDetail())
            }
    }
}