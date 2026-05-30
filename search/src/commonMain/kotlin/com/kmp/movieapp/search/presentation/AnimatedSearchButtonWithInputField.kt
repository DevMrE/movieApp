package com.kmp.movieapp.search.presentation

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import com.kmp.movieapp.core.util.focus_requester.requestFocusWithRetry

@Composable
fun SearchBar(
   searchText: String
) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val textFieldState = rememberTextFieldState(
        initialText = searchText
    )

    OutlinedTextField(
        state = textFieldState
    )

}

@Composable
private fun SearchFocusEffect(
    activeSearch: Boolean,
    focusRequester: FocusRequester,
    focusManager: FocusManager,
    clearInputField: () -> Unit
) {
    LaunchedEffect(activeSearch) {
        if (activeSearch) focusRequester.requestFocusWithRetry()
        else {
            clearInputField()
            focusManager.clearFocus()
        }
    }
}
