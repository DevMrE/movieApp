package com.kmp.movieapp.search.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.core.ui.theme.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.search.presentation.model.filter.UiGenre
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.filter_by_genre
import com.kmp.movieapp.shared.filter_header

@Composable
fun FilterGenreComponent(
    modifier: Modifier,
    genres: List<UiGenre>?,
    onGenreUpdate: (UiGenre) -> Unit
) {
    val verticalScrollState = rememberScrollState()

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = MaterialTheme.padding.defaultContentPadding),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.six),
        horizontalAlignment = Alignment.Start
    ) {

        FilterHeader(headerRes = Res.string.filter_header)

        FilterTitle(titleRes = Res.string.filter_by_genre)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(verticalScrollState),
            horizontalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.padding.six,
            ),
        ) {
            genres?.forEach { genre ->
                FilterChip(
                    selected = genre.selected,
                    onClick = {
                        onGenreUpdate(genre)
                    },
                    label = {
                        Text(genre.name)
                    },
                    shape = MaterialTheme.shapes.extraSmall,
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.primaryFixed,
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun FilterGenreComponentPrev() {
    AppTheme {
        FilterGenreComponent(
            modifier = Modifier,
            genres = listOf(
                UiGenre(
                    id = "1",
                    name = "Thriller",
                    selected = true
                ),
                UiGenre(
                    id = "1",
                    name = "Action",
                    selected = false
                ),
                UiGenre(
                    id = "1",
                    name = "Documentation",
                    selected = false
                ),
                UiGenre(
                    id = "1",
                    name = "Documentation",
                    selected = false
                ),
                UiGenre(
                    id = "1",
                    name = "Documentation",
                    selected = false
                ),
                UiGenre(
                    id = "1",
                    name = "Documentation",
                    selected = false
                ),
                UiGenre(
                    id = "1",
                    name = "Documentation",
                    selected = false
                ),
                UiGenre(
                    id = "1",
                    name = "Documentation",
                    selected = false
                ),
                UiGenre(
                    id = "1",
                    name = "Documentation",
                    selected = false
                ),
                UiGenre(
                    id = "1",
                    name = "Documentation",
                    selected = false
                ),
            )
        ) {


        }
    }
}