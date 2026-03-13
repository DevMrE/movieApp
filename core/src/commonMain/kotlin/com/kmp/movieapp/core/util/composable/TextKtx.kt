package com.kmp.movieapp.core.util.composable

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AutoResizeText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    minTextSize: TextUnit = 10.sp,
    maxTextSize: TextUnit = 20.sp,
    step: TextUnit = 1.sp,
    style: TextStyle = LocalTextStyle.current,
) {
    var textSize by remember { mutableStateOf(maxTextSize) }
    var readyToDraw by remember { mutableStateOf(false) }

    Text(
        text = text,
        maxLines = maxLines,
        modifier = modifier.drawWithContent {
            if (readyToDraw) drawContent()
        },
        softWrap = false,
        style = style.copy(fontSize = textSize),
        onTextLayout = { result ->
            if (!readyToDraw) {
                if (result.didOverflowWidth || result.didOverflowHeight) {
                    val next = textSize.value - step.value
                    if (next.sp <= minTextSize) {
                        textSize = minTextSize
                        readyToDraw = true
                    } else {
                        textSize = next.sp
                    }
                } else {
                    readyToDraw = true
                }
            }
        }
    )
}
