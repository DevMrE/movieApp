package com.kmp.movieapp.discover.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.discover.presentation.DiscoverAction
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.see_all
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SeeAllButton(
    mediaCategory: MediaCategory,
    onClick: (DiscoverAction) -> Unit
) {
    if (mediaCategory == MediaCategory.UNKNOWN) return
    TextButton(
        onClick = {
            onClick(DiscoverAction.OnSeeAllClicked(mediaCategory))
        },
    ) {
        Text(stringResource(Res.string.see_all), color = MaterialTheme.colorScheme.primary)
    }
}