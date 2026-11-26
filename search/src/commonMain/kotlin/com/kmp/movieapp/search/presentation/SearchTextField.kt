package com.kmp.movieapp.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.boolean.isFalse
import com.kmp.movieapp.search.Res
import com.kmp.movieapp.search.ic_close
import com.kmp.movieapp.search.ic_search
import com.kmp.movieapp.search.search_placeholder
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SearchTextField(
    query: String?,
    onQueryChanged: (String?) -> Unit,
    modifier: Modifier = Modifier,
) {

    OutlinedTextField(
        value = query ?: "",
        onValueChange = {
            onQueryChanged(it)
        },
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_search),
                contentDescription = null
            )
        },
        placeholder = {
            Text(
                text = stringResource(Res.string.search_placeholder),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        trailingIcon = {
            if (query.isNullOrEmpty().isFalse) {
                IconButton(
                    onClick = {
                        onQueryChanged(null)
                    },
                ) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.ic_close),
                        contentDescription = null
                    )
                }
            }
        },
        shape = MaterialTheme.shapes.extraLarge,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@PreviewLightDark
@Composable
private fun SearchTextFieldPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    horizontal = MaterialTheme.padding.ten,
                    vertical = MaterialTheme.padding.eighty
                )
        ) {
            SearchTextField(
                query = "",
                onQueryChanged = {},
                modifier = Modifier
            )
        }
    }
}