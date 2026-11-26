package com.kmp.movieapp.components.mediacard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.components.vote.VoteAverageComponent
import com.kmp.movieapp.core.ui.imageloader.ImageLoader
import com.kmp.movieapp.core.ui.material.gradient
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.mobileSize
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.composable.applyIf
import com.kmp.movieapp.core.util.composable.applyIfElse
import com.kmp.movieapp.core.util.composable.gradientOverlay
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.img_media_item_not_found
import org.jetbrains.compose.resources.painterResource

@Composable
fun MediaCardComponent(
    title: String,
    voteAverage: Double?,
    posterPath: String?,
    enableGradient: Boolean = true,
    bigCard: Boolean = false,
    height: Dp = MaterialTheme.mobileSize.defaultCardHeight,
    onClick: (() -> Unit)? = null,
) {
    Card(
        modifier = Modifier
            .height(height)
            .heightIn(height, )
            .applyIfElse(
                condition = bigCard,
                ifTrue = {
                    width(MaterialTheme.mobileSize.movieBigCardWidth)
                },
                ifFalse = {
                    width(MaterialTheme.mobileSize.defaultCardWidth)
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
                    modifier = Modifier
                        .applyIf(condition = enableGradient) {
                            gradientOverlay(brush = MaterialTheme.gradient.card)
                        },
                    loadingProgress = {}
                )
            } else {
                Image(
                    painter = painterResource(Res.drawable.img_media_item_not_found),
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
                color = MaterialTheme.colorScheme.primaryFixed,
            )

            VoteAverageComponent(
                voteAverage = voteAverage,
                modifier = Modifier
                    .applyIfElse(
                        condition = bigCard,
                        ifTrue = {
                            align(Alignment.TopEnd)
                                .padding(top = MaterialTheme.padding.sixteen)
                        },
                        ifFalse = {
                            align(Alignment.BottomEnd)
                                .padding(bottom = MaterialTheme.padding.sixteen)
                        }
                    )
                    .padding(
                        end = MaterialTheme.padding.twenty
                    )
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun MediaCardPreview() {
    AppTheme {
        MediaCardComponent(
            title = "Movie Title",
            voteAverage = 8.5,
            posterPath = null,
        )

    }
}