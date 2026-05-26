package com.kmp.movieapp.components.app_bar.topbar.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.ic_arrow_left
import com.kmp.movieapp.composeApp.ic_movie
import com.kmp.movieapp.core.ui.material.padding
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TopAppBarContent(
    title: String,
    showBackButton: Boolean = false,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateBack: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            LogoWithTitle(title)
        },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            AnimatedVisibility(showBackButton) {
                NavigationBackIcon(
                    navigateBack = {
                        navigateBack?.let { it() }
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            subtitleContentColor = MaterialTheme.colorScheme.primary
        )
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
private fun LogoWithTitle(title: String, modifier: Modifier = Modifier) {
    AnimatedContent(
        targetState = title,
    ) { animatedTitle ->
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.five)
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_movie),
                contentDescription = null
            )

            Text(
                text = animatedTitle,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.animateContentSize()
            )
        }
    }
}