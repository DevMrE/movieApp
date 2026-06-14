package com.kmp.movieapp.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.kmp.movieapp.search.Res
import com.kmp.movieapp.search.ic_close
import com.kmp.movieapp.search.ic_forword_arrow
import com.kmp.movieapp.search.search_placeholder
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    query: String,
    onQueryChanged: (String) -> Unit,
    visible: Boolean,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            initialOffsetX = { it }
        ) + expandHorizontally(
            expandFrom = Alignment.Start
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it }
        ) + shrinkHorizontally(
            shrinkTowards = Alignment.End
        ),
        modifier = modifier
    ) {

        TextField(
            value = query,
            onValueChange = onQueryChanged,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            singleLine = true,
            suffix = {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_close),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = MaterialTheme.shapes.small)
                        .clickable {
                            onQueryChanged("")
                        }
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_forword_arrow),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onClose()
                    }
                )
            },
            placeholder = {
                Text(
                    text = stringResource(Res.string.search_placeholder),
                    style = MaterialTheme.typography.labelSmall.copy(fontStyle = FontStyle.Italic)
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                showKeyboardOnFocus = true
            ),
            keyboardActions = KeyboardActions {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
            textStyle = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Black
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.background
            )
        )
    }
}