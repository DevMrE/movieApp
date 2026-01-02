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
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.movie.presentation.destination.MovieScreenSection
import com.kmp.navigation.compose.NavChildSectionsHost
import com.kmp.navigation.compose.rememberActiveChildSection
import com.kmp.navigation.compose.rememberNavigation
import com.kmp.series.presentation.destination.SeriesScreenSection
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val tab by viewModel.tabState.collectAsStateWithLifecycle()
    val navigation = rememberNavigation()
    val activeSection = rememberActiveChildSection(
        parentSection = HomeScreenSection,
        initialChild = MovieScreenSection
    )

    Column {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = MaterialTheme.padding.twentyFive),
            space = MaterialTheme.padding.thirtySix
        ) {
            SegmentedButton(
                selected = activeSection == MovieScreenSection,
                onClick = {
                    navigation.switchTo(MovieScreenSection)
                },
                shape = SegmentedButtonDefaults.baseShape,
                icon = {},
                colors = SegmentedButtonDefaults.colors(
                    activeBorderColor = MaterialTheme.colorScheme.primary,
                    activeContainerColor = MaterialTheme.colorScheme.primary,
                    activeContentColor = MaterialTheme.colorScheme.background,
                    inactiveContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    inactiveBorderColor = MaterialTheme.colorScheme.background,
                ),
            ) {
                Text(text = stringResource(resource = tab.movieTabResource))
            }

            SegmentedButton(
                selected = activeSection == SeriesScreenSection,
                onClick = {
                    navigation.switchTo(SeriesScreenSection)
                },
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

//        when (destination) {
//            MovieScreenDestination -> MovieScreen()
//            SeriesScreenDestination -> SeriesScreen()
//        }

        NavChildSectionsHost(parentSection = HomeScreenSection)
    }
}
