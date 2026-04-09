package com.kmp.movieapp.content_detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.content_detail.presentation.component.description.descriptionTab
import com.kmp.movieapp.content_detail.presentation.component.header.detailHeader
import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.navigation.compose.rememberNavigation
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ContentDetailScreen(
    id: String,
    contentType: ContentDetailType
) {
    val viewModel = koinViewModel<ContentDetailViewModel>(
        parameters = { parametersOf(id, contentType) },
        key = id
    )
    val uiState by viewModel.uiState.collectAsState()
    val navigation = rememberNavigation()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        detailHeader(
            title = uiState?.title ?: "",
            mediaInfo = uiState?.mediaInfo,
            posterPath = uiState?.posterPath ?: "",
            onBackClicked = {
                navigation.navigateUp()
            },
            onDetailAction = {

            }
        )
        descriptionTab(
            overview = uiState?.description
        )

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
            detailHeader(
                title = "Movie",
                mediaInfo = buildAnnotatedString {
                    append("1997")
                    append(" \u2022 ")
                    append("186")
                },
                posterPath = "",
                onBackClicked = {
                },
                onDetailAction = {
                }
            )

            descriptionTab(overview = "This is an long description text that describes the movie")
        }
    }
}