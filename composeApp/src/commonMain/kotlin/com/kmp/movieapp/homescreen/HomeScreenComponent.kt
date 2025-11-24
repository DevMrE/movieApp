package com.kmp.movieapp.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.kmp.movieapp.movie.presentation.route.MovieScreenDestination
import com.kmp.movieapp.movie.presentation.screen.mobile.MobileMovieScreenComponent
import com.kmp.navigation.compose_interface.RegisterNavigation
import com.kmp.navigation.compose_interface.navGraph
import com.kmp.navigation.destination.SeriesScreenDestination
import com.kmp.series.SeriesComponent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenComponent() {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val tab by viewModel.tabState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.padding.twentyFive),
            space = MaterialTheme.padding.thirtySix
        ) {
            SegmentedButton(
                selected = tab.selectedTab == MovieScreenDestination,
                onClick = viewModel::onTabChanged,
                shape = SegmentedButtonDefaults.baseShape,
                icon = {}
            ) {
                Text(text = stringResource(resource = tab.movieTabResource))
            }

            SegmentedButton(
                selected = tab.selectedTab == SeriesScreenDestination,
                onClick = viewModel::onTabChanged,
                shape = SegmentedButtonDefaults.baseShape,
                icon = {}
            ) {
                Text(text = stringResource(resource = tab.seriesTabResource))
            }
        }

        RegisterNavigation(
            startNavDestination = MovieScreenDestination
        ) {
            navGraph {
                screen<MovieScreenDestination> { navParameter ->
                    MobileMovieScreenComponent()
                }

                screen<SeriesScreenDestination> { data ->
                    SeriesComponent()
                }
            }
        }
    }
}
