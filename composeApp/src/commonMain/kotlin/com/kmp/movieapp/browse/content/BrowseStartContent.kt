package com.kmp.movieapp.browse.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.browse.BrowseAction
import com.kmp.movieapp.browse.BrowseViewModel
import com.kmp.movieapp.browse.component.FilterGenreComponent
import com.kmp.movieapp.components.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.browse_media_title
import com.kmp.movieapp.core.ui.material.padding
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseStartContent() {
    val viewModel = koinViewModel<BrowseViewModel>()
    val discover by viewModel.browseState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarContent(title = stringResource(Res.string.browse_media_title))
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.padding.ten,
                alignment = Alignment.CenterVertically
            )
        ) {
            FilterGenreComponent(
                modifier = Modifier,
                genres = discover?.genreFilter?.genres
            ) { genre ->
                viewModel.onAction(BrowseAction.OnGenreUpdated(genre))
            }

            BrowseContentList(
                modifier = Modifier.weight(1f),
                contentList = discover?.contentList,
                onContentClicked = {
                    viewModel.onAction(BrowseAction.OnContentClicked(uiMediaCard = it))
                }
            )
        }
    }
}