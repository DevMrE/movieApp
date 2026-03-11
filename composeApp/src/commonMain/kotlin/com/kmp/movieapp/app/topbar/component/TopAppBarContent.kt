package com.kmp.movieapp.app.topbar.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.navigation.compose.rememberNavigation
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.ic_back_arrow
import movieapp.composeapp.generated.resources.ic_close
import movieapp.composeapp.generated.resources.ic_movie
import movieapp.composeapp.generated.resources.ic_search
import movieapp.composeapp.generated.resources.search
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TopAppBarContent(
    title: String,
    showBackButton: Boolean = false,
) {
    val navigation = rememberNavigation()
    var search by remember { mutableStateOf("") }
    var activeSearch by remember { mutableStateOf(false) }

    val searchProgress by animateFloatAsState(
        targetValue = if (activeSearch) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    val titleAndIconAlpha = (1f - (searchProgress * 2f)).coerceIn(0f, 1f)
    val searchAlpha = (searchProgress * 2f).coerceIn(0f, 1f)

    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = MaterialTheme.padding.sixteen)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .graphicsLayer { alpha = titleAndIconAlpha },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    LogoWithTitle(title = title)
                    SearchIcon(onClick = { activeSearch = true })
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterEnd)
                        .graphicsLayer {
                            translationX = (1f - searchProgress) * size.width
                            alpha = searchAlpha
                        }
                ) {
                    SearchText(
                        search = search,
                        onTextChanged = { search = it },
                        onCloseClicked = {
                            activeSearch = !activeSearch
                            search = ""
                        }
                    )
                }
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { navigation.navigateUp() }) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.ic_back_arrow),
                        contentDescription = null,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            subtitleContentColor = MaterialTheme.colorScheme.primary
        )
    )
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

@Composable
private fun SearchIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_search),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SearchText(
    search: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    OutlinedTextField(
        value = search,
        onValueChange = onTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = MaterialTheme.padding.twenty),
        placeholder = { Text(stringResource(Res.string.search)) },
        singleLine = true,
        trailingIcon = {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_close),
                modifier = Modifier
                    .size(MaterialTheme.size.iconSize)
                    .clickable { onCloseClicked() },
                contentDescription = null,
                tint = Color.Blue
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background
        )
    )
}