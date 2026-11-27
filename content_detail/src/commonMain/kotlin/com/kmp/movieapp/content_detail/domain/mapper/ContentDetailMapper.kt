package com.kmp.movieapp.content_detail.domain.mapper

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.model.ContentDetailGenre
import com.kmp.movieapp.content_detail.domain.model.ContentImages
import com.kmp.movieapp.content_detail.domain.model.ContentInfo
import com.kmp.movieapp.content_detail.domain.model.ContentMetaData
import com.kmp.movieapp.content_detail.domain.model.ContentTitle
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.series.domain.model.Series

internal fun Movie.toContentDetail() = ContentDetail(
    info = ContentInfo(
        title = ContentTitle(
            localized = info.title,
            original = info.originTitle
        ),
        synopsis = info.overview
    ),
    metadata = ContentMetaData(
        adult = metaData.adult,
        runtime = metaData.runtime,
        releaseDate = metaData.releaseDate,
        genres = genres?.map {
            ContentDetailGenre(name = it.name)
        } ?: emptyList()
    ),
    images = ContentImages(
        posterPath = image.posterPath,
        backdropPath = image.backdropPath
    )
)

internal fun Series.toContentDetail() = ContentDetail(
    info = ContentInfo(
        title = ContentTitle(
            localized = name,
        ),
        synopsis = overview
    ),
    images = ContentImages(
        posterPath = posterPath,
        backdropPath = backdropPath
    )
)

