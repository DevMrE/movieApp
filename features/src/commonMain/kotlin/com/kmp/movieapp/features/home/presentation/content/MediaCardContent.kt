package com.kmp.movieapp.features.home.presentation.content

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp
import com.kmp.movieapp.core.ui.content.MediaItemCard
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.features.home.domain.model.HomeCategory
import com.kmp.movieapp.features.home.presentation.action.HomeAction
import com.kmp.movieapp.features.home.presentation.model.UiMedia

internal fun LazyListScope.mediaCardContent(
    width: Dp,
    category: HomeCategory,
    movieList: List<UiMedia> = emptyList(),
    onAction: (HomeAction) -> Unit
) {
    items(movieList) { movie ->
        val bigCard = width > MaterialTheme.size.movieCardWidth
        MediaItemCard(
            width = width,
            movieTitle = if (bigCard) movie.title else "",
            moviePosterPath = if (bigCard) movie.backdropPath else movie.posterPath,
            enableGradient = bigCard,
            onClick = {
                onAction(HomeAction.OnNavigateToDetailScreen(movie.id))
            },
        )
    }
}

