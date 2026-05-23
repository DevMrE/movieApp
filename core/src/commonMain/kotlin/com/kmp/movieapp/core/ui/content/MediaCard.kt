package com.kmp.movieapp.core.ui.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.core.Res
import com.kmp.movieapp.core.media_item_not_found
import com.kmp.movieapp.core.ui.imageloader.ImageLoader
import com.kmp.movieapp.core.ui.material.gradient
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.composable.applyIf
import com.kmp.movieapp.core.util.composable.applyIfElse
import com.kmp.movieapp.core.util.composable.gradientOverlay
import org.jetbrains.compose.resources.painterResource

@Composable
fun MediaCard(
    title: String,
    posterPath: String?,
    enableGradient: Boolean = true,
    bigCard: Boolean = false,
    height: Dp = MaterialTheme.size.defaultCardHeight,
    onClick: (() -> Unit)? = null,
) {
    Card(
        modifier = Modifier
            .height(height)
            .applyIfElse(
                condition = bigCard,
                ifTrue = {
                    width(MaterialTheme.size.movieBigCardWidth)
                },
                ifFalse = {
                    width(MaterialTheme.size.defaultCardWidth)
                }
            )
            .dropShadow(
                shape = MaterialTheme.shapes.extraLarge,
                shadow = Shadow(
                    color = MaterialTheme.colorScheme.onBackground,
                    radius = 4.dp,
                    spread = 3.dp,
                    alpha = 0.3f,
                )
            ),
        shape = MaterialTheme.shapes.extraLarge,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.extraLarge)
                .clickable(enabled = onClick != null) {
                    onClick?.let {
                        it()
                    }
                }
        ) {
            if (posterPath != null) {
                ImageLoader(
                    url = posterPath,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .applyIf(condition = enableGradient) {
                            gradientOverlay(brush = MaterialTheme.gradient.card)
                        }
                )
            } else {
                Image(
                    painter = painterResource(Res.drawable.media_item_not_found),
                    contentDescription = null
                )
            }

            Text(
                text = if (bigCard) title else "",
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

@PreviewLightDark()
@Composable
private fun MediaCardPrev() {
    val gridState = rememberLazyGridState()

    val list = (0..50).map {
        "Movie $it"
    }

    AppTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            state = gridState,
            contentPadding = PaddingValues(horizontal = MaterialTheme.padding.five, vertical = MaterialTheme.padding.five),
            verticalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.padding.twelfth,
                alignment = Alignment.CenterVertically
            ),
            horizontalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.padding.twelfth,
                alignment = Alignment.CenterHorizontally
            ),
        ) {
            items(list) {
                MediaCard(
                    title = it,
                    posterPath = null,
                )
            }
        }
    }
}