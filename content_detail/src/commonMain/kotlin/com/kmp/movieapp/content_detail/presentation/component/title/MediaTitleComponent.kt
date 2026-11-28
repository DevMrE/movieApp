package com.kmp.movieapp.content_detail.presentation.component.title

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.core.ui.theme.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.ui.theme.vynoraColors

@Composable
internal fun TitleWithMetaInfos(
    title: String,
    mediaInfo: AnnotatedString?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.padding.defaultContentPadding),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.six,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = title,
            color = MaterialTheme.vynoraColors.textPrimary,
            style = MaterialTheme.typography.titleLarge
        )

        mediaInfo?.let {
            Text(
                text = it,
                textAlign = TextAlign.Start,
                color = MaterialTheme.vynoraColors.textSecondary,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@PreviewLightDark
@Composable
fun MediaTitleSectionPreview() {
    AppTheme {
        LazyColumn(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) {
            item {
                TitleWithMetaInfos(
                    title = "Movie Title",
                    mediaInfo = buildAnnotatedString {
                        append("1986 \u2022 2h 13m")
                    }
                )
            }
        }
    }
}