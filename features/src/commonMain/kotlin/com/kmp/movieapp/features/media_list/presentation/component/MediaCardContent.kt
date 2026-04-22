package com.kmp.movieapp.features.media_list.presentation.component

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp
import com.kmp.movieapp.core.ui.content.MediaItemCard
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.features.Res
import com.kmp.movieapp.features.actor
import com.kmp.movieapp.features.content_type
import com.kmp.movieapp.features.home.presentation.action.HomeAction
import com.kmp.movieapp.features.media_list.presentation.model.UiMediaCard
import com.kmp.movieapp.features.movie
import com.kmp.movieapp.features.series
import com.kmp.movieapp.features.trending.domain.model.TrendingType
import com.kmp.movieapp.features.unknown
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.mediaCardContent(
    width: Dp,
    movieList: List<UiMediaCard> = emptyList(),
    onAction: (HomeAction) -> Unit
) {
    items(movieList) { movie ->
        val bigCard = width > MaterialTheme.size.movieCardWidth
        val contentRes = when (movie.type) {
            TrendingType.MOVIE -> Res.string.movie
            TrendingType.PEOPLE -> Res.string.actor
            TrendingType.SERIES -> Res.string.series
            else -> Res.string.unknown
        }

        val typeString = stringResource(contentRes)

        val title = stringResource(Res.string.content_type, typeString, movie.title)

        MediaItemCard(
            width = width,
            movieTitle = if (bigCard) title else "",
            moviePosterPath = if (bigCard) movie.backdropPath else movie.posterPath,
            enableGradient = bigCard,
            onClick = {
                onAction(HomeAction.OnNavigateToDetailScreen(movie.id))
            },
        )
    }
}

