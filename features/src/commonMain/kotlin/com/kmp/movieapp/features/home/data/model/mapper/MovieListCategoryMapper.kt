package com.kmp.movieapp.features.home.data.model.mapper

import com.kmp.movieapp.features.home.data.model.request.movie_lists.MovieListCategory
import com.kmp.movieapp.features.home.domain.model.MovieCategory

internal fun MovieCategory.toMovieListCategory(): MovieListCategory {
    return MovieListCategory.valueOf(name)
}
