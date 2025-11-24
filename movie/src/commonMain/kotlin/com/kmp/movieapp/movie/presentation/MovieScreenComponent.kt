package com.kmp.movieapp.movie.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.movie.presentation.component.PopularMovieComponent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieScreenComponent() {
    val viewModel = koinViewModel<MovieScreenViewModel>()
    val popularMovieList by viewModel.movieState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PopularMovieComponent(popularMovieList, onAction = {})
    }
}
