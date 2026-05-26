package com.kmp.movieapp.genre.data.model.request

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.genre.domain.model.GenreContentType
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_V3}/genre/{path}/list")
internal data class GenreRequestDto(
    @SerialName("path")
    val pathType: GenreContentType
)
