package com.kmp.movieapp.search.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.kmp.movieapp.core.presentation.material.size

@Composable
internal fun SearchIconButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            modifier = Modifier.size(MaterialTheme.size.iconSize),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
