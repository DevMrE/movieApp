package com.kmp.movieapp.discover.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

object DiscoverScreen {

    @Composable
    operator fun invoke() {
        val viewModel = koinViewModel<DiscoverViewModel>()
        val state by viewModel.discoverState.collectAsStateWithLifecycle()

        DiscoverComponent(
            discover = state,
            onAction = viewModel::onAction
        )
    }
}
