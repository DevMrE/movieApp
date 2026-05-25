package com.kmp.movieapp.search.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.search.Res
import com.kmp.movieapp.search.ic_close
import com.kmp.movieapp.search.ic_forword_arrow
import com.kmp.movieapp.search.ic_mic
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun CustomSearchField(
    modifier: Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onMicActive: () -> Unit,
    onBackClick: () -> Unit,
    focusRequester: FocusRequester
) {

    val borderColor = if (query.isNotEmpty() || focusRequester.captureFocus()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground

    val icon = if (query.isNotEmpty()) vectorResource(Res.drawable.ic_close)
    else vectorResource(Res.drawable.ic_mic)

    val iconClick = {
        if (query.isNotEmpty()) onQueryChange("") else onMicActive()
    }

    Surface(
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(28.dp),
        tonalElevation = 3.dp,
        color = MaterialTheme.colorScheme.background,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {

            SearchIconButton(
                icon = vectorResource(Res.drawable.ic_forword_arrow)
            ) {
                onBackClick()
            }

            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
            )

            SearchIconButton(
                icon = icon
            ) {
                iconClick()
            }
        }
    }
}