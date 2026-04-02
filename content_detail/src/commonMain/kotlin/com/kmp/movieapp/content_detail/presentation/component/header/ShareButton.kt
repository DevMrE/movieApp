package com.kmp.movieapp.content_detail.presentation.component.header

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.kmp.movieapp.content_detail.Res
import com.kmp.movieapp.content_detail.ic_share_button
import org.jetbrains.compose.resources.vectorResource

@Composable
fun ShareButton(
    onShareClicked: () -> Unit,
) {
    IconButton(
        onClick = onShareClicked
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_share_button),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}