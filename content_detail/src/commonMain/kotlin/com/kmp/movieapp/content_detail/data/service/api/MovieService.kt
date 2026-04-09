package com.kmp.movieapp.content_detail.data.service.api

import com.kmp.movieapp.content_detail.data.model.api.response.ContentDetailDto
import com.kmp.movieapp.core.network.util.Result

internal interface MovieService {

   suspend fun fetchMovieDetail(movieId: Int): Result<ContentDetailDto, Unit>
}