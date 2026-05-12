package com.kmp.movieapp.discover.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.discover.presentation.component.FilterComponent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DiscoverScreen() {

    val viewModel = koinViewModel<DiscoverViewModel>()
    val discover by viewModel.discoverState.collectAsStateWithLifecycle()

    FilterComponent(
        filters = discover?.filter,
        onFilterClicked = { uiFilter ->
            viewModel.onAction(DiscoverAction.OnFilterClicked(uiFilter))
        }
    )
}