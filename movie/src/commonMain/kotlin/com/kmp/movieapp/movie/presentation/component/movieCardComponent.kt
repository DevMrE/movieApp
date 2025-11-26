package com.kmp.movieapp.movie.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.kmp.movieapp.core.presentation.composable.gradientOverlay
import com.kmp.movieapp.core.util.imageloader.ImageLoader
import com.kmp.movieapp.core.presentation.material.gradient
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.model.UiMovie

fun LazyListScope.movieCardComponent(
    bigCard: Boolean,
    movieList: List<UiMovie>,
    onAction: (MovieAction) -> Unit
) {
    items(movieList) { movie ->
        val width =
            if (bigCard) MaterialTheme.size.moviePosterWidth else MaterialTheme.size.movieCardWidth
        Card(
            modifier = Modifier
                .height(MaterialTheme.size.movieCardHeight)
                .width(width)
                .clip(MaterialTheme.shapes.extraLarge)
                .clickable {
                    onAction(MovieAction.OnNavigateToDetailScreen(movie.id))
                },
            shape = CardDefaults.elevatedShape
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                ImageLoader(
                    url = movie.posterPath,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.gradientOverlay(MaterialTheme.gradient.card)
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(
                            horizontal = MaterialTheme.padding.fifteen,
                            vertical = MaterialTheme.padding.twentyFive
                        ),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.five)
                ) {
                    Text(
                        text = movie.title,
                        color = MaterialTheme.colorScheme.surface,
                        maxLines = 2,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = movie.genre,
                        color = MaterialTheme.colorScheme.surface,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

