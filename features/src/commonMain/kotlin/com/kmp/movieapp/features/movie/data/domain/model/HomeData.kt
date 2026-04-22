package com.kmp.movieapp.features.movie.data.domain.model

import com.kmp.movieapp.features.movie.domain.model.Movie
import com.kmp.movieapp.features.trending.domain.model.Trending

data class HomeData(
    val trendingList: List<Trending>? = emptyList(),
    val popularMovies: List<Movie>? = emptyList()
)