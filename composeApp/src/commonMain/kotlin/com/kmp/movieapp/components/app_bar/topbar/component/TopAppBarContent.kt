package com.kmp.movieapp.components.app_bar.topbar.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarState
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.ic_arrow_left
import com.kmp.movieapp.composeApp.ic_movie
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.util.boolean.isFalse
import com.kmp.movieapp.core.util.logger.logE
import com.kmp.movieapp.search.presentation.SearchTextField
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.vectorResource

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3ExpressiveApi::class
)
@Composable
fun TopAppBarContent(
    title: String,
    showTitle: Boolean = true,
    showBackButton: Boolean = false,
    query: String? = null,
    focusRequester: FocusRequester? = null,
    searchBarState: SearchBarState? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onQueryChanged: (String) -> Unit = {},
    topBarAction: (@Composable () -> Unit)? = null,
    navigateBack: (() -> Unit)? = null,
) {
    val scope = rememberCoroutineScope()

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LogoWithTitle(
                    title = title,
                    visible = if (showTitle.isFalse) searchBarState?.currentValue == SearchBarValue.Collapsed else showTitle
                )

                SearchTextField(
                    query = query ?: "",
                    onQueryChanged = onQueryChanged,
                    visible = searchBarState?.currentValue == SearchBarValue.Expanded,
                    focusRequester = focusRequester ?: return@Row,
                    modifier = Modifier.weight(1f),
                    onClose = {
                        scope.launch {
                            runCatching {
                                searchBarState?.animateToCollapsed()
                            }.onFailure {
                                logE("Catching error during collapsing search bar!")
                            }
                        }
                    },
                )
            }
        },
        navigationIcon = {
            AnimatedVisibility(showBackButton) {
                NavigationBackIcon(
                    navigateBack = {
                        navigateBack?.let { it() }
                    }
                )
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            subtitleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            topBarAction?.let { it() }
        }
    )
}

@Composable
private fun NavigationBackIcon(
    navigateBack: () -> Unit
) {
    IconButton(onClick = navigateBack) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_arrow_left),
            contentDescription = null
        )
    }
}

@Composable
private fun LogoWithTitle(
    title: String?,
    visible: Boolean
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandHorizontally(),
        exit = fadeOut() + shrinkHorizontally()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.five)
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_movie),
                contentDescription = null
            )

            title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        }
    }
}