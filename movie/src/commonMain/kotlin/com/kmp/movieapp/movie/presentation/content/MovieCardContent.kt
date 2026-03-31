package com.kmp.movieapp.movie.presentation.content

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp
import com.kmp.movieapp.core.ui.content.MediaItemCard
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.model.UiMovie

internal fun LazyListScope.movieCardContent(
    width: Dp,
    movieList: List<UiMovie> = emptyList(),
    onAction: (MovieAction) -> Unit
) {
    items(movieList) { movie ->
        MediaItemCard(
            width = width,
            movieTitle = movie.title,
            moviePosterPath = if (width > MaterialTheme.size.movieCardWidth) movie.backdropPath else movie.posterPath,
            onClick = {
                onAction(MovieAction.OnNavigateToDetailScreen(movie.title))
            }
        )
    }
}

