package com.kmp.movieapp.search.data.mapper

import com.kmp.movieapp.core.data.url.UrlHelper
import com.kmp.movieapp.search.data.model.api.response.SearchDto
import com.kmp.movieapp.search.domain.model.Info
import com.kmp.movieapp.search.domain.model.MediaImages
import com.kmp.movieapp.search.domain.model.Rating
import com.kmp.movieapp.search.domain.model.Search

internal fun SearchDto.toSearch() = Search(
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
    rating = Rating(
        average = voteAverage ?: 0.0,
        count = voteCount ?: 0,
        popularity = popularity ?: 0.0
    ),
    genres = genreIds?.map { it } ?: emptyList()
)

internal fun Iterable<SearchDto>?.mapToSearch(): List<Search> =
    this?.map { it.toSearch() } ?: emptyList()
