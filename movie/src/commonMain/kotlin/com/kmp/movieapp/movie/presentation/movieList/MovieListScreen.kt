package com.kmp.movieapp.movie.presentation.movieList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.movieapp.movie.presentation.content.MovieCard
import com.kmp.movieapp.movie.presentation.destination.MovieDetailDestination
import com.kmp.navigation.compose.rememberNavigation
import org.koin.compose.viewmodel.koinViewModel

@Suppress("FrequentlyChangingValue")
@Composable
fun PopularMovieListScreen() {

    val viewModel = koinViewModel<MovieListViewModel>()
    val popularMovieList = viewModel.movieListState.collectAsStateWithLifecycle()
    val navigation = rememberNavigation()
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        state = gridState,
        contentPadding = PaddingValues(horizontal = MaterialTheme.padding.five),
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.ten,
            alignment = Alignment.CenterVertically
        ),
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.ten,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        items(items = popularMovieList.value, key = { it.id }) { movie ->
            MovieCard(
                width = MaterialTheme.size.movieCardWidth,
                height = MaterialTheme.size.movieCardLstHeight,
                movieTitle = movie.title,
                moviePosterPath = movie.posterPath,
                onClick = {
                    navigation.navigateTo(MovieDetailDestination(id = movie.id.toString()))
                }
            )
        }
    }
}