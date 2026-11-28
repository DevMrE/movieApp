package com.kmp.movieapp.core.ui.style

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.ui.theme.vynoraColors

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun cardStyle(): Style {
    val backgroundColor = MaterialTheme.vynoraColors.surface
    val shape = MaterialTheme.shapes.extraLarge
    val padding = MaterialTheme.padding.defaultContentPadding
    val borderColor = MaterialTheme.vynoraColors.border

    return {
        clip(true)
        shape(value = shape)
        background(backgroundColor)
        borderWidth(1.5.dp)
        borderColor(borderColor)
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@PreviewLightDark
@Composable
private fun CardStylePreview() {
    val styleState = rememberStyleState()
    AppTheme {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .size(300.dp)
                    .styleable(
                        styleState = styleState,
                        style = cardStyle()
                    )
            ) {

            }
        }
    }
}