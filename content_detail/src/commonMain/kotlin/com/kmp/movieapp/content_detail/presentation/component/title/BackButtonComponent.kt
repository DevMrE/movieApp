package com.kmp.movieapp.content_detail.presentation.component.title

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kmp.movieapp.content_detail.Res
import com.kmp.movieapp.content_detail.ic_back_arrow
import com.kmp.movieapp.core.ui.material.padding
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun BackButton(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {

    IconButton(
        modifier = modifier
            .systemBarsPadding()
            .padding(start = MaterialTheme.padding.defaultContentPadding)
            .background(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                shape = MaterialTheme.shapes.extraLarge
            ),
        onClick = onBackClicked,
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_back_arrow),
            contentDescription = null,
            tint = Color.White
        )
    }
}