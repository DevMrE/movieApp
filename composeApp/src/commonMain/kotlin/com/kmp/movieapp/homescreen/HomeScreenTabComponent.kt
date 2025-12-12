package com.kmp.movieapp.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.movie.presentation.MovieScreen
import com.kmp.movieapp.movie.presentation.destination.MovieScreenDestination
import com.kmp.navigation.NavDestination
import com.kmp.series.presentation.SeriesScreen
import com.kmp.series.presentation.destination.SeriesScreenDestination
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenTabComponent(
    navDestination: NavDestination
) {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val tab by viewModel.tabState.collectAsStateWithLifecycle()

    Column {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.padding.twentyFive),
            space = MaterialTheme.padding.thirtySix
        ) {
            SegmentedButton(
                selected = navDestination == MovieScreenDestination,
                onClick = { viewModel.onTabChanged(navDestination = MovieScreenDestination) },
                shape = SegmentedButtonDefaults.baseShape,
                icon = {},
                colors = SegmentedButtonDefaults.colors(
                    activeBorderColor = MaterialTheme.colorScheme.primary,
                    activeContainerColor = MaterialTheme.colorScheme.primary,
                    activeContentColor = MaterialTheme.colorScheme.background,
                    inactiveContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    inactiveBorderColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(text = stringResource(resource = tab.movieTabResource))
            }

            SegmentedButton(
                selected = navDestination == SeriesScreenDestination,
                onClick = { viewModel.onTabChanged(navDestination = SeriesScreenDestination) },
                shape = SegmentedButtonDefaults.baseShape,
                icon = {},
                colors = SegmentedButtonDefaults.colors(
                    activeBorderColor = MaterialTheme.colorScheme.primary,
                    activeContainerColor = MaterialTheme.colorScheme.primary,
                    activeContentColor = MaterialTheme.colorScheme.background,
                    inactiveContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    inactiveBorderColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(text = stringResource(resource = tab.seriesTabResource))
            }
        }

        when (navDestination) {
            MovieScreenDestination -> MovieScreen()
            SeriesScreenDestination -> SeriesScreen()
            else -> Unit
        }
    }
}
