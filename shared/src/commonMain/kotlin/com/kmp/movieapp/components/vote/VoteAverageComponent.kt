package com.kmp.movieapp.components.vote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.double.roundTo
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.ic_star
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun VoteAverageComponent(
    voteAverage: Double?,
    modifier: Modifier = Modifier
) {
    if (voteAverage == null || voteAverage == 0.0) return

    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .clip(shape = MaterialTheme.shapes.extraLargeIncreased)
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
            .padding(MaterialTheme.padding.six),
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.six,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_star),
            contentDescription = null,
            modifier = Modifier.size(MaterialTheme.size.voteAverageIconSize),
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "${voteAverage.roundTo(1)}",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@PreviewLightDark
@Composable
private fun VoteAveragePreview() {
    AppTheme {
        VoteAverageComponent(8.6)
    }
}