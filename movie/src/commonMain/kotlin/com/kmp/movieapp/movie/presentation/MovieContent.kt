package com.kmp.movieapp.movie.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.content.movieListContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieContent() {
    val viewModel = koinViewModel<MovieScreenViewModel>()
    val movieScreenState by viewModel.movieScreenState.collectAsStateWithLifecycle()

    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = movieScreenState?.isLoading.isTrue,
        onRefresh = {viewModel.onAction(MovieAction.OnRefresh)}
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.thirty),
            contentPadding = PaddingValues(vertical = MaterialTheme.padding.thirty)
        ) {
            movieScreenState?.let { screen ->
                movieListContent(
                    uiMovieList = screen.nowPlaying,
                    onAction = viewModel::onAction
                )

                movieListContent(
                    uiMovieList = screen.popularMovie,
                    onAction = viewModel::onAction
                )

                movieListContent(
                    uiMovieList = screen.topRatedMovies,
                    onAction = viewModel::onAction
                )
            }

            item {
                Spacer(Modifier.height(MaterialTheme.size.bottomBarHeight))
            }
        }
    }
}
