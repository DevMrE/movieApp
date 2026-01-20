package com.kmp.movieapp.movie.presentation.content

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.model.UiMovie

fun LazyListScope.movieCardContent(
    bigCard: Boolean,
    movieList: List<UiMovie>,
    onAction: (MovieAction) -> Unit
) {
    items(movieList) { movie ->
        MovieCard(
            bigCard = bigCard,
            movieTitle = movie.title,
            moviePosterPath = movie.posterPath,
            onClick = {
                onAction(MovieAction.OnNavigateToDetailScreen(movie.id))
            }
        )
    }
}

