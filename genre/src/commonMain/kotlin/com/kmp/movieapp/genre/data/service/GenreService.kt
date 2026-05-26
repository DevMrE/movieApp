package com.kmp.movieapp.genre.data.service

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.util.Result
import com.kmp.movieapp.genre.data.model.response.GenreDto
import com.kmp.movieapp.genre.domain.model.GenreContentType

internal interface GenreService {

    suspend fun fetchGenres(type: GenreContentType): Result<List<GenreDto>, ApiError>

}