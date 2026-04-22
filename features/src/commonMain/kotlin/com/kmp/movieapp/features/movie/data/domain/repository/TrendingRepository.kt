package com.kmp.movieapp.features.movie.data.domain.repository

interface TrendingRepository {

    fun getAllTrending()

    fun getMovieTrending()

    fun getSeriesTrending()
}