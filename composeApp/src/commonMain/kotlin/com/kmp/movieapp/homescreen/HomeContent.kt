package com.kmp.movieapp.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.homescreen.destination.HomeTabs
import com.kmp.movieapp.homescreen.model.UiTabState
import com.kmp.movieapp.movie.presentation.destination.MovieDestination
import com.kmp.navigation.compose.NavigationTabs
import com.kmp.navigation.compose.rememberActiveTabIn
import com.kmp.navigation.compose.rememberNavigation
import com.kmp.series.presentation.destination.SeriesDestination
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.movie_tab
import movieapp.composeapp.generated.resources.series_tab
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeContent() {
    Logger.i(tag = "KmpNavigation", messageString = "HomeScreen")

    val tab by remember {
        mutableStateOf(UiTabState(movieTabResource = Res.string.movie_tab, seriesTabResource = Res.string.series_tab))
    }

    val navigation = rememberNavigation()
    val navDestination = rememberActiveTabIn<HomeTabs>()

    Logger.i(tag = "KmpNavigation", messageString = "HomeContent destination: $navDestination")

    Column {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.padding.twentyFive),
            space = MaterialTheme.padding.thirtySix
        ) {
            SegmentedButton(
                selected = navDestination == MovieDestination,
                onClick = {
                    navigation.navigateTo(MovieDestination)
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
                selected = navDestination == SeriesDestination,
                onClick = {
                    navigation.navigateTo(SeriesDestination)
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

        NavigationTabs<HomeTabs>()
    }
}
