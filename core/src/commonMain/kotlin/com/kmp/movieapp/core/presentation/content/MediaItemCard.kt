package com.kmp.movieapp.core.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.core.Res
import com.kmp.movieapp.core.media_item_not_found
import com.kmp.movieapp.core.presentation.material.gradient
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.movieapp.core.util.composable.gradientOverlay
import com.kmp.movieapp.core.util.imageloader.ImageLoader
import org.jetbrains.compose.resources.painterResource

@Composable
fun MediaItemCard(
    width: Dp,
    movieTitle: String,
    moviePosterPath: String?,
    height: Dp = MaterialTheme.size.movieCardHeight,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .height(height)
            .width(width),
        shape = MaterialTheme.shapes.extraLarge,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.extraLarge)
                .clickable {
                    onClick()
                }
        ) {
            if (moviePosterPath != null) {
                ImageLoader(
                    url = moviePosterPath,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.gradientOverlay(brush = MaterialTheme.gradient.card)
                )
            } else {
                Image(
                    painter = painterResource(Res.drawable.media_item_not_found),
                    contentDescription = null
                )
            }

            Text(
                text = movieTitle,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        horizontal = MaterialTheme.padding.fifteen,
                        vertical = MaterialTheme.padding.twentyFive
                    ),
                maxLines = 2,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}