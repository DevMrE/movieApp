package com.kmp.movieapp.content_detail.domain.repository

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import kotlinx.coroutines.flow.Flow

interface ContentDetailRepository {

    suspend fun getMovieDetail(contentId: Int): Flow<ContentDetail>

    suspend fun getSeriesDetail(contentId: Int): Flow<ContentDetail>
}