package com.kmp.movieapp.content_detail.data.repository.api

import com.kmp.movieapp.content_detail.domain.mapper.toContentDetail
import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import com.kmp.movieapp.series.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ContentDetailRepImpl(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
) : ContentDetailRepository {

    override suspend fun getMovieDetail(contentId: Int): Flow<ContentDetail?> =
        movieRepository.getMovieForId(contentId)
            .map { movie ->
                movie?.toContentDetail()
            }

    override suspend fun getSeriesDetail(contentId: Int): Flow<ContentDetail?> =
        seriesRepository.getSerieForId(contentId).map {
            it?.toContentDetail()
        }
}