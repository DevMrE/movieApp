package com.kmp.movieapp.core.ui.component.text

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.kmp.movieapp.core.Res
import com.kmp.movieapp.core.expand_text
import com.kmp.movieapp.core.shrink_text
import com.kmp.movieapp.core.ui.theme.material.padding
import com.kmp.movieapp.core.ui.theme.vynoraColors
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    collapsedMaxLines: Int = 3,
    textAlign: TextAlign = TextAlign.Justify,
    color: Color = MaterialTheme.vynoraColors.textSecondary,
    style: TextStyle = MaterialTheme.typography.labelSmall
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var hasOverflow by remember { mutableStateOf(false) }
    val expandText = stringResource(Res.string.expand_text)
    val shrinkText = stringResource(Res.string.shrink_text)

    Column(
        modifier = modifier.animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.tiny)
    ) {
        Text(
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else collapsedMaxLines,
            textAlign = textAlign,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { hasOverflow = it.hasVisualOverflow },
            style = style
        )

        if (hasOverflow || expanded) {
            Text(
                text = if (expanded) shrinkText else expandText,
                color = color,
                modifier = Modifier.clickable {
                    expanded = !expanded
                },
            )
        }
    }
}