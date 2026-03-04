package com.kmp.movieapp.movie.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.movie.presentation.content.MovieScreenContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieContent() {
    val viewModel = koinViewModel<MovieScreenViewModel>()
    val movieScreenState by viewModel.movieScreenState.collectAsStateWithLifecycle()

    MovieScreenContent(movieScreenState = movieScreenState, onAction = viewModel::onAction)
}
