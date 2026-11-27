package com.kmp.movieapp.movie.data.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.genre.domain.model.Genre
import com.kmp.movieapp.movie.data.model.response.discover.FilterMovieResponse
import com.kmp.movieapp.movie.data.model.response.movie.MovieResponse
import com.kmp.movieapp.movie.data.model.response.popular_movie.PopularMovieResponse
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieGenre
import com.kmp.movieapp.movie.domain.model.MovieImage
import com.kmp.movieapp.movie.domain.model.MovieInfo
import com.kmp.movieapp.movie.domain.model.MovieMetaData

internal fun PopularMovieResponse.toMovieImage() = MovieImage(
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
)

internal fun PopularMovieResponse.toMovieInfo() = MovieInfo(
    title = "$title",
    originTitle = "$originalTitle",
    overview = "$overview",
)

/**
 * Mapper for transforming an [PopularMovieResponse] into
 * [Movie]
 */
internal fun PopularMovieResponse.toMovie(genre: List<Genre>) =
    Movie(
        id = id ?: 0,
        info = toMovieInfo(),
        metaData = MovieMetaData(
            adult = adult ?: false,
            releaseDate = releaseDate,
            runtime = null
        ),
        image = toMovieImage(),
        genres = genre
            .filter { g -> genreIds?.contains(g.id).isTrue }
            .map { currentGenre ->
                MovieGenre(currentGenre.name)
            },
        voteAverage = voteAverage ?: 0.0,
    )

/**
 * Mapper for transforming an [MovieResponse] into
 * [Movie]
 */
internal fun MovieResponse.toMovie() = Movie(
    id = id ?: 0,
    info = toMovieInfo(),
    image = toMovieImage(),
    metaData = MovieMetaData(
        adult = adult ?: false,
        releaseDate = releaseDate,
        runtime = null
    ),
    voteAverage = voteAverage ?: 0.0,
    genres = genres?.toMovieGenres()
)


internal fun MovieResponse.toMovieImage(): MovieImage = MovieImage(
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
)

internal fun MovieResponse.toMovieInfo(): MovieInfo = MovieInfo(
    title = "$title",
    originTitle = "$originalTitle",
    overview = "$overview",
)

internal fun FilterMovieResponse.toMovie(): Movie = Movie(
    id = id ?: 0,
    info = MovieInfo(
        title = "$title",
        originTitle = "$originalTitle",
        overview = "$overview",
    ),
    image = MovieImage(
        posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
        backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath"
    ),
    metaData = MovieMetaData(
        adult = adult ?: false,
        releaseDate = releaseDate,
        runtime = null
    ),
    type = MediaCategory.MOVIE,
    voteAverage = voteAverage ?: 0.0
)

