package com.kmp.movieapp.content_detail.data.repository.api

import com.kmp.movieapp.content_detail.data.mapper.toContentDetail
import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import com.kmp.series.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ContentDetailRepImpl(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) : ContentDetailRepository {

    override suspend fun getMovieDetail(contentId: String): Flow<ContentDetail> = flow {
        movieRepository.getMovieForId(contentId.toInt(), language = "en")
            .collect {
                if (it != null) emit(it.toContentDetail())
            }
    }

    override suspend fun getSeriesDetail(contentId: String): Flow<ContentDetail> = flow {
        seriesRepository.getSeriesForId(seriesId = contentId.toInt(), language = "en")
            .collect { series ->
                series?.let {
                    logI<ContentDetailRepository>(message = "Series: ${series.name}")
                }
                if (series != null) emit(series.toContentDetail())
            }
    }
}