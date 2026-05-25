package com.kmp.movieapp.content_detail.presentation.component.title

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme

@Composable
internal fun MediaTitle(
    modifier: Modifier = Modifier,
    title: String,
    mediaInfo: AnnotatedString?,
) {
    Row(
        modifier = modifier
            .padding(MaterialTheme.padding.defaultContentPadding)
            .padding(MaterialTheme.padding.six),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.defaultContentPadding,
            alignment = Alignment.Start
        )
    ) {
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
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )

            mediaInfo?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun MediaTitleSectionPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            MediaTitle(
                modifier = Modifier,
                title = "Movie Title",
                mediaInfo = buildAnnotatedString {
                    append("1986 \u2022 2h 13m")
                }
            )
        }
    }
}