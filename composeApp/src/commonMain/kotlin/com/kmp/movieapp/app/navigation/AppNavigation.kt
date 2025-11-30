package com.kmp.movieapp.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.homescreen.HomeScreenTabComponent
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.movie.presentation.destination.MovieDetailDestination
import com.kmp.movieapp.movie.presentation.destination.MovieScreenDestination
import com.kmp.movieapp.settings.SettingsScreenComponent
import com.kmp.movieapp.settings.destination.SettingsScreenDestination
import com.kmp.movieapp.settings.destination.SettingsSection
import com.kmp.navigation.compose.rememberNavigation
import com.kmp.navigation.registerNavigation
import com.kmp.series.presentation.destination.SeriesScreenDestination
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.back_arrow
import org.jetbrains.compose.resources.vectorResource

fun registerAppNavigation() {
    registerNavigation(startDestination = MovieScreenDestination) {
        section<HomeScreenSection>(root = MovieScreenDestination) {
            screen<MovieScreenDestination> { navDestination ->
                HomeScreenTabComponent(navDestination)
            }

            screen<SeriesScreenDestination> { navDestination ->
                HomeScreenTabComponent(navDestination)
            }

            screen<MovieDetailDestination> { detailDestination ->
                val navigation = rememberNavigation()
                Box(modifier = Modifier.fillMaxSize()) {
                    IconButton(onClick = { navigation.navigateUp() }) {
                        Icon(
                            imageVector = vectorResource(Res.drawable.back_arrow),
                            contentDescription = null
                        )
                    }
                    Text("$detailDestination.id", modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        section<SettingsSection>(root = SettingsScreenDestination) {
            screen<SettingsScreenDestination> {
                SettingsScreenComponent()
            }
        }
    }
}