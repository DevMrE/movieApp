package com.kmp.movieapp.movie.presentation.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.core.util.action.Action
import com.kmp.movieapp.movie.presentation.model.UiMovieScreen

@Composable
fun MovieScreenContent(
    movieScreenState: UiMovieScreen?,
    onAction: (Action) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.thirty),
        contentPadding = PaddingValues(vertical = MaterialTheme.padding.thirty)
    ) {
        movieScreenState?.let { screen ->
            movieListContent(
                uiMovieList = screen.nowPlaying,
                onAction = onAction
            )

            movieListContent(
                uiMovieList = screen.popularMovie,
                onAction = onAction
            )

            movieListContent(
                uiMovieList = screen.topRatedMovies,
                onAction = onAction
            )
        }
    }
}
