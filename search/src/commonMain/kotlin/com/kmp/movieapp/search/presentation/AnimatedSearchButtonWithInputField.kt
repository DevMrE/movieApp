package com.kmp.movieapp.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.util.focus_requester.requestFocusWithRetry
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.Route
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import com.kmp.movieapp.search.Res
import com.kmp.movieapp.search.ic_search
import com.kmp.movieapp.search.presentation.action.SearchAction
import com.kmp.movieapp.search.presentation.component.CustomSearchField
import com.kmp.movieapp.search.presentation.component.SearchIconButton
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AnimatedSearchButtonWithInputField(
    animatedExitContent: (@Composable () -> Unit)? = null,
) {
    val viewModel = koinViewModel<SearchViewModel>()
    val uiState by viewModel.searchQueryState.collectAsStateWithLifecycle()

    val navigator: Navigator<Route> = koinInject()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    SearchFocusEffect(
        activeSearch = uiState.isSearchActive,
        focusRequester = focusRequester,
        focusManager = focusManager,
        clearInputField = {
            viewModel.onAction(SearchAction.OnSearchChanged(""))
        }
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = MaterialTheme.padding.sixteen)
    ) {

        val fullWidth = maxWidth

        val searchWidth by animateDpAsState(
            targetValue = if (uiState.isSearchActive) fullWidth else 0.dp,
            animationSpec = tween(delayMillis = 250),
            label = "searchWidth"
        )

        val showSearchIcon = searchWidth < 4.dp

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (animatedExitContent != null) {
                AnimatedVisibility(
                    visible = !uiState.isSearchActive,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    animatedExitContent()
                }

                Spacer(Modifier.weight(1f))
            }

            AnimatedVisibility(
                visible = !uiState.isSearchActive && showSearchIcon,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                SearchIconButton(
                    icon = vectorResource(Res.drawable.ic_search)
                ) {
                    viewModel.onAction(SearchAction.OnSearchActiveChanged)
                    navigator.navigateTo(AppNavigation.Search)
                }
            }
        }

        CustomSearchField(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(searchWidth)
                .animateContentSize(tween(350)),
            query = uiState.search,
            onQueryChange = { query ->
                viewModel.onAction(SearchAction.OnSearchChanged(query))
            },
            onMicActive = {},
            onBackClick = {
                viewModel.onAction(SearchAction.OnSearchActiveChanged)
                navigator.navigateBack()
            },
            focusRequester = focusRequester
        )

        AnimatedVisibility(
            visible = showSearchIcon,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            SearchIconButton(
                icon = vectorResource(Res.drawable.ic_search),
                modifier = Modifier.align(Alignment.CenterEnd)
                    .animateContentSize()
            ) {
                viewModel.onAction(SearchAction.OnSearchActiveChanged)
                navigator.navigateTo(AppNavigation.Search)
            }
        }
    }
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
