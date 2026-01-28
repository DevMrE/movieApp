package com.kmp.movieapp.app.bottombar.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.core.presentation.material.padding

@Composable
internal fun NavIconWithShine(
    selected: Boolean,
    icon: ImageVector,
    label: String,
) {
    val glowAlpha = if (selected) 1f else 0f
    val glowColor = LocalContentColor.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.two)
    ) {
        Box(
            modifier = Modifier.size(52.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer(alpha = glowAlpha)
                    .blur(20.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    .drawBehind {
                        val r = size.minDimension * 0.30f      // Ring-radius
                        val w = size.minDimension * 0.22f      // Ring-thickness
                        drawCircle(
                            color = glowColor.copy(alpha = 0.85f),
                            radius = r,
                            center = center,
                            style = Stroke(width = w)
                        )
                    }
            )

            Icon(imageVector = icon, contentDescription = null)
        }

        Text(
            text = label,
            fontWeight = FontWeight.ExtraBold
        )
    }
}