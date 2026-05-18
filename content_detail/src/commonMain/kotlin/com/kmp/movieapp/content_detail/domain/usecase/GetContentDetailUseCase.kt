package com.kmp.movieapp.content_detail.domain.usecase

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetContentDetailUseCase(
    private val contentDetailRepository: ContentDetailRepository
) {

    suspend operator fun invoke(
        contentType: MediaCategory,
        contentId: String
    ): Flow<ContentDetail?> {
        val id = contentId.toIntOrNull()

        return if (id != null) {
            when (contentType) {
                MediaCategory.MOVIE -> contentDetailRepository.getMovieDetail(id)
                MediaCategory.SERIES -> contentDetailRepository.getSeriesDetail(id)
                else -> {
                    // TODO: Add here the repository for person
                    contentDetailRepository.getMovieDetail(id)
                }
            }
        } else flowOf(null)
    }
}