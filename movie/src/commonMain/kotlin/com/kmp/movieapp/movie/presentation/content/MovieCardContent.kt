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

        val bigCard = width > MaterialTheme.size.movieCardWidth
        MediaItemCard(
            width = width,
            movieTitle = if (bigCard) movie.title else "",
            moviePosterPath = if (bigCard) movie.backdropPath else movie.posterPath,
            enableGradient = bigCard,
            onClick = {
                onAction(MovieAction.OnNavigateToDetailScreen(movie.id))
            },
        )
    }
}

