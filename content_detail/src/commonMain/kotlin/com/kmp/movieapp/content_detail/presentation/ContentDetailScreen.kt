package com.kmp.movieapp.content_detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.kmp.movieapp.content_detail.presentation.component.synopsis.Synopsis
import com.kmp.movieapp.content_detail.presentation.component.title.ContentImage
import com.kmp.movieapp.content_detail.presentation.component.title.TitleWithMetaInfos
import com.kmp.movieapp.content_detail.presentation.formatter.toMetaInfoString
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.ui.theme.vynoraColors
import com.kmp.movieapp.core.util.koin.koinComposableViewModel
import com.kmpalette.extensions.network.rememberNetworkDominantColorState
import io.ktor.http.Url
import org.koin.core.parameter.parametersOf

@Composable
fun ContentDetailScreen(
    id: String,
    mediaCategory: MediaCategory,
    onBackClicked: () -> Unit
) {
    val viewModel = koinComposableViewModel<ContentDetailViewModel>(
        parameters = { parametersOf(id, mediaCategory) },
        key = id
    )
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val dynamicColorState = rememberNetworkDominantColorState(
        defaultColor = MaterialTheme.colorScheme.surface,
        defaultOnColor = MaterialTheme.colorScheme.onSurface,
        cacheSize = 1000,
        coroutineContext = scope.coroutineContext
    )

    uiState?.let { state ->
        if (state.uiContentDetail == null) {
            return Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Text("Something went wrong. Details could not load.")
            }
        }

        LaunchedEffect(state.uiContentDetail.posterPath) {
            dynamicColorState.updateFrom(Url(state.uiContentDetail.posterPath))
        }

        ContentDetail(
            title = state.uiContentDetail.title,
            metaInfo = state.uiContentDetail.toMetaInfoString(),
            posterPath = state.uiContentDetail.posterPath,
            plot = state.uiContentDetail.description,
            onBackClicked = onBackClicked
        )
    } ?: Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun ContentDetail(
    title: String,
    metaInfo: AnnotatedString,
    posterPath: String,
    plot: String,
    onBackClicked: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.vynoraColors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        contentImageWithTitleArea(
            posterPath = posterPath,
            title = title,
            metaInfo = metaInfo,
            onBackClicked = onBackClicked
        ) {
            isLoading = it
        }

        synopsis(plot)
    }
}

private fun LazyListScope.contentImageWithTitleArea(
    title: String,
    posterPath: String,
    metaInfo: AnnotatedString?,
    onBackClicked: () -> Unit,
    isLoading: (Boolean) -> Unit,
) {
    item {
        Box {
            ContentImage(
                posterPath = posterPath,
                onBackClicked = onBackClicked,
                isLoading = isLoading
            )

            TitleWithMetaInfos(
                title = title,
                mediaInfo = metaInfo,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = MaterialTheme.padding.defaultContentPadding)
            )
        }
    }
}

private fun LazyListScope.synopsis(
    overview: String
) {
    item {
        Synopsis(overview = overview)
    }
}


@Composable
@PreviewLightDark
private fun ContentDetailScreenPreview() {
    AppTheme {

        val description = LoremIpsum(100)
        ContentDetail(
            title = "Movie",
            metaInfo = buildAnnotatedString {
                append("1997")
                append(" \u2022 ")
                append("186")
            },
            posterPath = "",
            plot = "${description.values.lastOrNull()}",
            onBackClicked = {}
        )
    }
}