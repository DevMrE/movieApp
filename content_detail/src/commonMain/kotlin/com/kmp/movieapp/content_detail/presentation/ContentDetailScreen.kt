package com.kmp.movieapp.content_detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.content_detail.presentation.component.media_buttons.mediaButtons
import com.kmp.movieapp.content_detail.presentation.component.overview.overview
import com.kmp.movieapp.content_detail.presentation.component.title.title
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.core.util.boolean.isTrue
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ContentDetailScreen(
    id: String,
    mediaCategory: MediaCategory?,
    onBackClicked: () -> Unit
) {
    val viewModel = koinViewModel<ContentDetailViewModel>(
        parameters = { parametersOf(id, mediaCategory) },
        key = id
    )
    val uiState by viewModel.uiState.collectAsState()
    val isLoading = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        title(
            title = uiState?.title ?: "",
            mediaInfo = uiState?.mediaInfo,
            posterPath = uiState?.posterPath ?: "",
            onBackClicked = onBackClicked
        ) {
            isLoading.value = it
        }

        overview(uiState?.description ?: "")
    }

    if (isLoading.value.isTrue) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
@PreviewLightDark
private fun ContentDetailScreenPreview() {
    AppTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            title(
                title = "Movie",
                mediaInfo = buildAnnotatedString {
                    append("1997")
                    append(" \u2022 ")
                    append("186")
                },
                posterPath = "",
                onBackClicked = {
                }
            ) {}

            mediaButtons(
                onPlayClicked = {},
                onShareClicked = {

                }
            )

            overview("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.")
        }
    }
}