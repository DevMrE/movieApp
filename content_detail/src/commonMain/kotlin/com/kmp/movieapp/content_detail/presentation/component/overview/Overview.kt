package com.kmp.movieapp.content_detail.presentation.component.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.content_detail.Res
import com.kmp.movieapp.content_detail.content_plot_title
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.synopsis(
    overview: String
) {
    item {
        Spacer(Modifier.padding(vertical = MaterialTheme.padding.defaultContentPadding))
        Column {
            Text(
                text = stringResource(Res.string.content_plot_title),
                modifier = Modifier.padding(horizontal = MaterialTheme.padding.defaultContentPadding),
                color = MaterialTheme.colorScheme.onPrimaryFixed,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = overview,
                modifier = Modifier.padding(horizontal = MaterialTheme.padding.defaultContentPadding),
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@PreviewLightDark
@Composable
internal fun OverviewPreview() {
    AppTheme {
        Surface {
            LazyColumn {
                synopsis("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.")
            }
        }
    }
}