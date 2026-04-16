package com.kmp.movieapp.homescreen

import androidx.compose.runtime.Composable
import com.kmp.movieapp.homescreen.destination.HomeTabs
import com.kmp.movieapp.movie.presentation.MovieContent
import com.kmp.navigation.compose.rememberActiveTabIn
import com.kmp.navigation.compose.rememberNavigation

@Composable
fun HomeContent() {

    val navigation = rememberNavigation()
    val navDestination = rememberActiveTabIn<HomeTabs>()


    MovieContent()

}
