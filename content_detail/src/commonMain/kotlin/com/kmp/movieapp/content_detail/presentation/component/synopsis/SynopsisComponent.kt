package com.kmp.movieapp.content_detail.presentation.component.synopsis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.kmp.movieapp.content_detail.Res
import com.kmp.movieapp.content_detail.content_plot_title
import com.kmp.movieapp.core.ui.theme.material.padding
import com.kmp.movieapp.core.ui.style.cardStyle
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.ui.theme.vynoraColors
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
internal fun Synopsis(
    overview: String,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = Modifier.padding(MaterialTheme.padding.defaultContentPadding)
    ) {
        Card(
            modifier = modifier.styleable(
                style = cardStyle()
            )
        ) {
            Column(
                modifier = Modifier.padding(MaterialTheme.padding.defaultContentPadding),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.defaultContentPadding)
            ) {
                Text(
                    text = stringResource(Res.string.content_plot_title),
                    color = MaterialTheme.vynoraColors.textPrimary,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = overview,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.vynoraColors.textSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun SynopsisPreview() {
    AppTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Synopsis(
                "${LoremIpsum(100).values.lastOrNull()}"
            )
        }
    }
}
