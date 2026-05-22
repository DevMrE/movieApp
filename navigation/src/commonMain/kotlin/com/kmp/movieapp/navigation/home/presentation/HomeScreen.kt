package com.kmp.movieapp.navigation.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

object HomeScreen {

    @Composable
    operator fun invoke() {
        val viewModel = koinViewModel<HomeScreenViewModel>()
        val state by viewModel.movieScreenState.collectAsStateWithLifecycle()

        HomeContent(state, viewModel::onAction)
    }
}