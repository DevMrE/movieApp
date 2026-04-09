package com.kmp.movieapp.movie.data.model.request.discover

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.DISCOVER_ENDPOINT}${UrlHelper.MOVIE_ENDPOINT}")
internal data class DiscoverMoviesRequestDto(
    @SerialName("language")
    val language: String = "en",
    @SerialName("sort_by")
    val sortBy: SortByRequestDto = SortByRequestDto.POPULARITY_DESC,
    @SerialName("page")
    val page: Int = 1,
    @SerialName("with_genres")
    val genresIds: List<String>? = null,
    @SerialName("include_adult")
    val includeAdult: Boolean = false,
    @SerialName("include_video")
    val includeVideo: Boolean = false,
    @SerialName("year")
    val year: Int? = null
)