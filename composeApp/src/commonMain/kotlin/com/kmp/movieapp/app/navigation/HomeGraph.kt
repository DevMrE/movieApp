package com.kmp.movieapp.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.kmpnavigation.compose_interface.TypedGraphBuilder
import com.kmp.movieapp.homescreen.HomeScreenTabComponent
import com.kmp.movieapp.homescreen.destination.HomeScreenDestination
import com.kmp.movieapp.movie.presentation.route.MovieDetailDestination
import com.kmp.movieapp.movie.presentation.route.MovieScreenDestination
import com.kmp.series.presentation.destination.SeriesDetailScreenDestination
import com.kmp.series.presentation.destination.SeriesScreenDestination

/**
 * NavGraph for the home screen with [HomeScreenDestination] as the parent screen
 * and [MovieScreenDestination] as the initial screen from this parent.
 *
 * This [homeGraph] have [MovieScreenDestination], [SeriesScreenDestination],
 * [MovieDetailDestination] and []
 */
fun TypedGraphBuilder.homeGraph() {
    section<HomeScreenDestination, MovieScreenDestination> {
        screen<MovieScreenDestination> {
            HomeScreenTabComponent(MovieScreenDestination)
        }

        screen<SeriesScreenDestination> {
            HomeScreenTabComponent(SeriesScreenDestination)
        }

        screen<MovieDetailDestination> { detail ->
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "${detail.id}", modifier = Modifier.align(Alignment.Center))
            }
        }

        screen<SeriesDetailScreenDestination> { detail ->
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "SeriesDetail with id: ${detail.id}",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}