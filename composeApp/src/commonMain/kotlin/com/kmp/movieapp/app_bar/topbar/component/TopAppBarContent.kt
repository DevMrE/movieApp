package com.kmp.movieapp.app_bar.topbar.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.search.presentation.AnimatedSearchContent
import com.kmp.navigation.compose.rememberNavigation
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.ic_back_arrow
import movieapp.composeapp.generated.resources.ic_movie
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TopAppBarContent(
    title: String,
    showBackButton: Boolean = false,
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.padding.ten)
            .animateContentSize(),
        title = {
            AnimatedSearchContent {
                LogoWithTitle(title)
            }
        },
        navigationIcon = {
            if (showBackButton) {
                NavigationBackIcon()
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            subtitleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
private fun NavigationBackIcon() {
    val navigation = rememberNavigation()

    IconButton(onClick = { navigation.navigateUp() }) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_back_arrow),
            contentDescription = null
        )
    }
}

@Composable
private fun LogoWithTitle(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.five)
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_movie),
            contentDescription = null
        )
        Text(text = title, style = MaterialTheme.typography.headlineMedium)
    }
}