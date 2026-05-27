package com.kmp.movieapp.search.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import com.kmp.movieapp.core.util.focus_requester.requestFocusWithRetry

//@Composable
//fun AnimatedSearchButtonWithInputField(
//    animatedExitContent: (@Composable () -> Unit)? = null,
//) {
//    val viewModel = koinViewModel<SearchViewModel>()
//    val uiState by viewModel.searchQueryState.collectAsStateWithLifecycle()
//
//    val navigator: Navigator<Route> = koinInject()
//    val focusRequester = remember { FocusRequester() }
//    val focusManager = LocalFocusManager.current
//
//    SearchFocusEffect(
//        activeSearch = uiState.isSearchActive,
//        focusRequester = focusRequester,
//        focusManager = focusManager,
//        clearInputField = {
//            viewModel.onAction(SearchAction.OnSearchChanged(""))
//        }
//    )
//
//    BoxWithConstraints(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(end = MaterialTheme.padding.sixteen)
//    ) {
//
//        val fullWidth = maxWidth
//
//        val searchWidth by animateDpAsState(
//            targetValue = if (uiState.isSearchActive) fullWidth else 0.dp,
//            animationSpec = tween(delayMillis = 250),
//            label = "searchWidth"
//        )
//
//        val showSearchIcon = searchWidth < 4.dp
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            if (animatedExitContent != null) {
//                AnimatedVisibility(
//                    visible = !uiState.isSearchActive,
//                    enter = fadeIn(),
//                    exit = fadeOut()
//                ) {
//                    animatedExitContent()
//                }
//
//                Spacer(Modifier.weight(1f))
//            }
//
//            AnimatedVisibility(
//                visible = !uiState.isSearchActive && showSearchIcon,
//                enter = fadeIn(),
//                exit = fadeOut()
//            ) {
//                SearchIconButton(
//                    icon = vectorResource(Res.drawable.ic_search)
//                ) {
//                }
//            }
//        }
//
//        CustomSearchField(
//            modifier = Modifier
//                .align(Alignment.CenterEnd)
//                .width(searchWidth)
//                .animateContentSize(tween(350)),
//            query = uiState.search,
//            onQueryChange = { query ->
//                viewModel.onAction(SearchAction.OnSearchChanged(query))
//            },
//            onMicActive = {},
//            onBackClick = {
//                viewModel.onAction(SearchAction.OnSearchActiveChanged)
//                navigator.navigateBack()
//            },
//            focusRequester = focusRequester
//        )
//
//        AnimatedVisibility(
//            visible = showSearchIcon,
//            enter = fadeIn(),
//            exit = fadeOut(),
//            modifier = Modifier.align(Alignment.CenterEnd)
//        ) {
//            SearchIconButton(
//                icon = vectorResource(Res.drawable.ic_search),
//                modifier = Modifier.align(Alignment.CenterEnd)
//                    .animateContentSize()
//            ) {
//            }
//        }
//    }
//}

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
