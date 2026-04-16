package com.kmp.movieapp.features.home.data.model.mapper

import com.kmp.movieapp.features.home.data.model.request.movie_lists.MovieListCategory
import com.kmp.movieapp.features.home.domain.model.HomeCategory

internal fun HomeCategory.toMovieListCategory(): MovieListCategory {
    return MovieListCategory.valueOf(name)
}
