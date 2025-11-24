package com.kmp.movieapp.movie.data.model.mapper

import com.kmp.movieapp.movie.data.model.request.MovieListCategory
import com.kmp.movieapp.movie.domain.model.MovieCategory


fun MovieCategory.toMovieListCategory(): MovieListCategory {
    return MovieListCategory.valueOf(name)
}
