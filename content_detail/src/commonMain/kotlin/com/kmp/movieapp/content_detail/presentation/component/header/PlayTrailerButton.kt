package com.kmp.movieapp.content_detail.presentation.component.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.content_detail.Res
import com.kmp.movieapp.content_detail.ic_play_button
import com.kmp.movieapp.content_detail.play_button
import com.kmp.movieapp.core.ui.material.padding
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun PlayTrailerButton(
    modifier: Modifier,
    onPlayClicked: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onPlayClicked
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.defaultContentPadding)
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_play_button),
                contentDescription = null,
            )

            Text(text = stringResource(Res.string.play_button))
        }
    }
}