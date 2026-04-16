package com.kmp.movieapp.content_detail.data.repository.api

import com.kmp.movieapp.content_detail.data.mapper.toContentDetail
import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ContentDetailRepImpl(
    private val movieRepository: MovieRepository,
) : ContentDetailRepository {

    override suspend fun getMovieDetail(contentId: String): Flow<ContentDetail> = flow {
        movieRepository.getMovieForId(contentId.toInt(), language = "en")
            .collect {
                if (it != null) emit(it.toContentDetail())
            }
    }
}