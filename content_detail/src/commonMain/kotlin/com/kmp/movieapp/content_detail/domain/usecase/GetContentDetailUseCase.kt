package com.kmp.movieapp.content_detail.domain.usecase

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import kotlinx.coroutines.flow.Flow

class GetContentDetailUseCase(
    private val contentDetailRepository: ContentDetailRepository
) {

    suspend operator fun invoke(
        contentType: MediaCategory,
        contentId: String
    ): Flow<ContentDetail> =
        when (contentType) {
            MediaCategory.MOVIE -> contentDetailRepository.getMovieDetail(contentId)
            MediaCategory.SERIES -> contentDetailRepository.getSeriesDetail(contentId)
            else -> {
                // TODO: Add here the repository for person
                contentDetailRepository.getMovieDetail(contentId)
            }
        }
}