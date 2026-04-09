package com.kmp.movieapp.content_detail.domain.usecase

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.core.content_type.model.ContentDetailType
import kotlinx.coroutines.flow.Flow

class GetContentDetail(
    private val contentDetailRepository: ContentDetailRepository
) {

    suspend operator fun invoke(
        contentType: ContentDetailType,
        contentId: String
    ): Flow<ContentDetail> =
        when (contentType) {
            ContentDetailType.MOVIE -> contentDetailRepository.getMovieDetail(contentId)
            ContentDetailType.SERIES -> contentDetailRepository.getSeriesDetail(contentId)
            ContentDetailType.PERSON -> {
                // TODO: Add here the repository for person
                contentDetailRepository.getMovieDetail(contentId)
            }
        }
}