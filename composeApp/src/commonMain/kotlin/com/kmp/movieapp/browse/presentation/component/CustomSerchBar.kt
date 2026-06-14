package com.kmp.movieapp.browse.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.fillWidth
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.ic_close
import com.kmp.movieapp.composeApp.search
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.style.rememberStyleState
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.boolean.isTrue
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun CustomSearchBar(
    query: String?,
    focusManager: FocusManager,
    onQueryUpdate: (String) -> Unit
) {
    val styleState = rememberStyleState()
    val borderColor = MaterialTheme.colorScheme.onBackground.copy(0.6f)

    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.padding.defaultContentPadding)
            .styleable(
                styleState,
            ) {
                fillWidth()
                borderColor(value = borderColor)
            },
        value = query ?: "",
        onValueChange = {
            onQueryUpdate(it)
        },
        placeholder = {
            Text(
                text = stringResource(Res.string.search),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontStyle = FontStyle.Italic
                )
            )
        },
        trailingIcon = {
            if (query?.isNotEmpty().isTrue) {
                IconButton(
                    onClick = {
                        onQueryUpdate("")
                        focusManager.clearFocus(force = true)
                    },
                    content = {
                        Icon(
                            imageVector = vectorResource(Res.drawable.ic_close),
                            contentDescription = null
                        )
                    }
                )
            }
        }
    )
}


@PreviewLightDark
@Preview
@Composable
private fun CustomSearchBarPreview() {
    AppTheme {
        CustomSearchBar(
            query = "",
            focusManager = LocalFocusManager.current
        ) {

        }
    }
}