package com.kmp.movieapp.movie.data.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.genre.domain.model.Genre
import com.kmp.movieapp.movie.data.model.response.discover.DiscoverMovieDto
import com.kmp.movieapp.movie.data.model.response.movie.MovieDto
import com.kmp.movieapp.movie.data.model.response.movie_for_category.MovieForCategoryDto
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieGenre
import com.kmp.movieapp.movie.domain.model.MovieImage
import com.kmp.movieapp.movie.domain.model.MovieInfo

internal fun MovieForCategoryDto.toMovieImage() = MovieImage(
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
)

internal fun MovieForCategoryDto.toMovieInfo() = MovieInfo(
    title = "$title",
    overview = "$overview"
)

/**
 * Mapper for transforming an [MovieForCategoryDto] into
 * [Movie]
 */
internal fun MovieForCategoryDto.toMovie(genre: List<Genre>) =
    Movie(
        id = id ?: 0,
        movieInfo = toMovieInfo(),
        releaseDate = releaseDate,
        movieImage = toMovieImage(),
        genres = genre
            .filter { g -> genreIds?.contains(g.id).isTrue }
            .map { currentGenre ->
                MovieGenre(currentGenre.name)
            },
        runtime = null,
    )

/**
 * Mapper for transforming an [MovieDto] into
 * [Movie]
 */
internal fun MovieDto.toMovie() =
    Movie(
        id = id ?: 0,
        movieInfo = toMovieInfo(),
        movieImage = toMovieImage(),
        runtime = runtime,
        releaseDate = releaseDate,
        genres = genres?.toMovieGenres()
    )


internal fun MovieDto.toMovieImage(): MovieImage = MovieImage(
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
)

internal fun MovieDto.toMovieInfo(): MovieInfo = MovieInfo(
    title = "$title",
    overview = "$overview"
)

internal fun DiscoverMovieDto.toMovie(): Movie = Movie(
    id = id ?: 0,
    movieInfo = MovieInfo(
        title = "$title",
        overview = "$overview"
    ),
    movieImage = MovieImage(
        posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
        backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath"
    ),
    type = MediaCategory.MOVIE,
    releaseDate = releaseDate,
    voteAverage = voteAverage
)

