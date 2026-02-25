package com.kmp.movieapp.movie.presentation.content

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.Dp
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.model.UiMovie

fun LazyListScope.movieCardContent(
    width: Dp,
    movieList: List<UiMovie>,
    onAction: (MovieAction) -> Unit
) {
    items(movieList) { movie ->
        MovieCard(
            width = width,
            movieTitle = movie.title,
            moviePosterPath = movie.posterPath,
            onClick = {
                onAction(MovieAction.OnNavigateToDetailScreen(movie.id))
            }
        )
    }
}

