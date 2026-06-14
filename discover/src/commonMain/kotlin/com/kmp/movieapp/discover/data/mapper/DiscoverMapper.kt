package com.kmp.movieapp.discover.data.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.discover.data.model.response.DiscoverMoviesDto
import com.kmp.movieapp.discover.data.model.response.DiscoverSeriesDto
import com.kmp.movieapp.discover.domain.model.DiscoverContent
import com.kmp.movieapp.discover.domain.model.DiscoverGenre
import com.kmp.movieapp.discover.domain.model.DiscoverType
import com.kmp.movieapp.genre.domain.model.Genre

internal fun DiscoverMoviesDto.toDiscoverMovies(genre: List<Genre>) = DiscoverContent(
    title = title ?: "",
    id = id ?: 0,
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    type = MediaCategory.MOVIE,
    discoverGenres = genre
        .filter { g -> genreIds?.contains(g.id).isTrue }
        .map {
            DiscoverGenre(it.id, it.name, DiscoverType.MOVIES)
        }
)

internal fun DiscoverSeriesDto.toDiscoverSeries(genre: List<Genre>) = DiscoverContent(
    title = name ?: "",
    id = id ?: 0,
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    type = MediaCategory.SERIES,
    discoverGenres = genre
        .filter { g -> genreIds?.contains(g.id).isTrue }
        .map {
            DiscoverGenre(it.id, it.name, DiscoverType.SERIES)
        }
)
