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
import com.kmp.movieapp.movie.presentation.destination.MovieScreenDestination
import com.kmp.movieapp.movie.presentation.screen.mobile.MobileMovieScreenComponent
import com.kmp.navigation.NavDestination
import com.kmp.series.presentation.SeriesComponent
import com.kmp.series.presentation.destination.SeriesScreenDestination
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenTabComponent(
    selectedDestination: NavDestination
) {
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
                selected = selectedDestination == MovieScreenDestination,
                onClick = { viewModel.onTabChanged(MovieScreenDestination) },
                shape = SegmentedButtonDefaults.baseShape,
                icon = {}
            ) {
                Text(text = stringResource(resource = tab.movieTabResource))
            }

            SegmentedButton(
                selected = selectedDestination == SeriesScreenDestination,
                onClick = { viewModel.onTabChanged(SeriesScreenDestination) },
                shape = SegmentedButtonDefaults.baseShape,
                icon = {}
            ) {
                Text(text = stringResource(resource = tab.seriesTabResource))
            }
        }

        when (selectedDestination) {
            MovieScreenDestination -> MobileMovieScreenComponent()
            SeriesScreenDestination -> SeriesComponent()
            else -> Unit
        }
    }
}
