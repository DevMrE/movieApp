package com.kmp.movieapp.movie.data.model.mapper

import com.kmp.movieapp.movie.data.model.request.movie_lists.MovieListCategory
import com.kmp.movieapp.movie.domain.model.MovieCategory

internal fun MovieCategory.toMovieListCategory(): MovieListCategory {
    return MovieListCategory.valueOf(name)
}
