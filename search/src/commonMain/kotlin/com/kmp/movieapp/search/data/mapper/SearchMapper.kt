package com.kmp.movieapp.search.data.mapper

import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.search.data.model.api.response.MediaTypeDto
import com.kmp.movieapp.search.data.model.api.response.SearchDto
import com.kmp.movieapp.search.domain.model.Info
import com.kmp.movieapp.search.domain.model.MediaImages
import com.kmp.movieapp.search.domain.model.Rating
import com.kmp.movieapp.search.domain.model.Search

/**
 * Maps a single object [SearchDto] to [Search]
 */
internal fun SearchDto.toSearch() = Search(
    id = id.toString(),
    contentDetailType = when(mediaType) {
        MediaTypeDto.PERSON -> ContentDetailType.PERSON
        MediaTypeDto.TV -> ContentDetailType.SERIES
        else -> ContentDetailType.MOVIE
    },
    titleInfo = Info(
        originalTitle = originalTitle ?: "",
        mainTitle = title ?: "",
        originalLanguage = originalLanguage ?: "",
        overview = overview ?: ""
    ),
    media = MediaImages(
        posterUrl = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
        backdropUrl = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    ),
    releaseDate = releaseDate ?: "",
    genres = genreIds?.map { it } ?: emptyList(),
    rating = Rating(
        average = voteAverage ?: 0.0,
        count = voteCount ?: 0,
        popularity = popularity ?: 0.0
    )
)

/**
 * Maps a sequence of [SearchDto] to a [List] of [Search]
 * objects.
 */
internal fun Iterable<SearchDto>?.mapToSearch(): List<Search> =
    this?.map { it.toSearch() } ?: emptyList()
