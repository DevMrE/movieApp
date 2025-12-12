package com.kmp.movieapp.movie.presentation.content

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.core.presentation.composable.gradientOverlay
import com.kmp.movieapp.core.presentation.material.gradient
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.movieapp.core.util.imageloader.ImageLoader
import com.kmp.movieapp.movie.Res
import com.kmp.movieapp.movie.movie_not_found
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.model.UiMovie
import org.jetbrains.compose.resources.painterResource

fun LazyListScope.movieCardContent(
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
                .clickable {
                    onAction(MovieAction.OnNavigateToDetailScreen(movie.id))
                },
            shape = MaterialTheme.shapes.extraLarge,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = MaterialTheme.shapes.extraLarge)
            ) {
                if (movie.posterPath != null) {
                    ImageLoader(
                        url = movie.posterPath,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.gradientOverlay(brush = MaterialTheme.gradient.card)
                    )
                } else {
                    Image(
                        painter = painterResource(Res.drawable.movie_not_found),
                        contentDescription = null
                    )
                }

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
                        maxLines = 2,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = movie.genre,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

