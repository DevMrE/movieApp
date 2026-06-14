package com.kmp.movieapp.core.ui.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun searchBarStyle(): Style {
    val backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
    val shape = MaterialTheme.shapes.extraLarge
    return {
        shape(value = shape)
        background(color = backgroundColor)
    }
}