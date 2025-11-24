package com.kmp.movieapp.core.presentation.composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush

/**
 * Adds an gradient overlay over the actual content.
 */
fun Modifier.gradientOverlay(brush: Brush) = this.then(
    other = Modifier.drawWithCache {
        onDrawWithContent {
            // Show first the actual content
            drawContent()
            // add the brush over the content
            drawRect(brush = brush)
        }
    }
)