package com.kmp.movieapp.features.home.domain.repository

interface TrendingRepository {

    fun getAllTrending()

    fun getMovieTrending()

    fun getSeriesTrending()
}