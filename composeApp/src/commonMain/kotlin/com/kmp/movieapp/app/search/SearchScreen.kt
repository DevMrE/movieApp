package com.kmp.movieapp.app.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.app.search.action.SearchAction
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.movieapp.core.util.focus_requester.requestFocusWithRetry
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.ic_close
import movieapp.composeapp.generated.resources.ic_forword_arrow
import movieapp.composeapp.generated.resources.ic_mic
import movieapp.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AnimatedSearchContent(
    animatedExitContent: (@Composable () -> Unit)? = null
) {
    val viewModel = koinViewModel<SearchViewModel>()
    val uiState by viewModel.searchQueryState.collectAsStateWithLifecycle()

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
                }
            }
        }

        CustomSearchField(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(searchWidth)
                .animateContentSize(tween(350)),
            query = uiState.search,
            onQueryChange = {
                viewModel.onAction(SearchAction.OnSearchChanged(it))
            },
            onMicActive = {},
            onBackClick = {
                viewModel.onAction(SearchAction.OnSearchActiveChanged)
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

@Composable
internal fun SearchIconButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            modifier = Modifier.size(MaterialTheme.size.iconSize),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CustomSearchField(
    modifier: Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onMicActive: () -> Unit,
    onBackClick: () -> Unit,
    focusRequester: FocusRequester
) {

    val borderColor =
        if (query.isNotEmpty()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground

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
                textStyle = MaterialTheme.typography.bodyLarge
            )

            val icon = if (query.isNotEmpty()) vectorResource(Res.drawable.ic_close)
            else vectorResource(Res.drawable.ic_mic)

            val iconClick = {
                if (query.isNotEmpty()) onQueryChange("") else onMicActive()
            }

            SearchIconButton(
                icon = icon
            ) {
                iconClick()
            }
        }
    }
}