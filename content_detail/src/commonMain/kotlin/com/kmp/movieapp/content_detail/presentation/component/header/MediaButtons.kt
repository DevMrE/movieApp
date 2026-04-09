package com.kmp.movieapp.content_detail.presentation.component.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.content_detail.presentation.action.DetailAction
import com.kmp.movieapp.core.ui.content.MediaItemCard
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.core.ui.theme.AppTheme

@Composable
private fun MediaButtons(
    onPlayClicked: () -> Unit,
    onShareClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.padding.defaultContentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.defaultContentPadding)
    ) {
        PlayTrailerButton(onPlayClicked = onPlayClicked, modifier = Modifier.weight(1f))

        ShareButton(onShareClicked = onShareClicked)
    }
}

@Composable
private fun MediaTitle(
    title: String,
    mediaInfo: AnnotatedString?,
    posterPath: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.defaultContentPadding,
            alignment = Alignment.Start
        )
    ) {
        MediaItemCard(
            width = MaterialTheme.size.movieCardWidth,
            movieTitle = "",
            moviePosterPath = posterPath,
            enableGradient = false
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.padding.six,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.titleLarge
            )

            mediaInfo?.let {
                Text(
                    text = it,
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
internal fun MediaInfoContent(
    modifier: Modifier,
    title: String,
    posterPath: String,
    mediaInfo: AnnotatedString?,
    onDetailAction: (DetailAction) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = MaterialTheme.padding.defaultContentPadding),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.sixteen),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MediaTitle(
            title = title,
            mediaInfo = mediaInfo,
            posterPath = posterPath
        )

        MediaButtons(
            onPlayClicked = {
                onDetailAction(DetailAction.OnPlayClicked)
            },
            onShareClicked = {
                onDetailAction(DetailAction.OnSharedClicked)
            }
        )
    }
}

@PreviewLightDark
@Composable
fun MediaInfoContentPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            MediaInfoContent(
                modifier = Modifier,
                title = "Movie Title",
                posterPath = "",
                mediaInfo = buildAnnotatedString {
                    append("1986")
                    append(" \u2022 ")
                    append("2h 13m")
                }
            ) {}
        }
    }
}