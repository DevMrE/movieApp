package com.kmp.movieapp.content_detail.presentation.component.media_buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.ui.material.padding

internal fun LazyListScope.mediaButtons(
    onPlayClicked: () -> Unit,
    onShareClicked: () -> Unit
) {
    item {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.defaultContentPadding)
        ) {
            ShareButton(onShareClicked = onShareClicked)
        }
    }
}