package com.kmp.movieapp.movie.presentation.movieList

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.detail.presentation.destination.DetailNavDestination
import com.kmp.movieapp.movie.presentation.content.MovieCard
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
        state = gridState
    ) {
        items(popularMovieList.value) { movie ->
            MovieCard(
                bigCard = false,
                movieTitle = movie.title,
                moviePosterPath = movie.posterPath,
                onClick = {
                    navigation.navigateTo(DetailNavDestination(movieId = movie.id))
                }
            )
        }
    }
}