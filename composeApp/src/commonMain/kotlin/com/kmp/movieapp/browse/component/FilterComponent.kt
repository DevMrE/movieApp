package com.kmp.movieapp.browse.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.browse.model.UiFilter
import com.kmp.movieapp.browse.model.UiFilterType
import com.kmp.movieapp.browse.model.UiGenre
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterComponent(
    filters: List<UiFilter>?,
    onFilterClicked: (UiFilter) -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = MaterialTheme.padding.defaultContentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.ten,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        filters?.forEach { filter ->
            FilterChip(
                selected = filter.isSeclected,
                onClick = {
                    onFilterClicked(filter)
                },
                label = {
                    Text(filter.name)
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    selectedContainerColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun FilterComponentPrev() {
    AppTheme {
        FilterComponent(
            filters = listOf(
                UiFilter(
                    name = "Genre",
                    filterType = UiFilterType.ByGenre(
                        genres = listOf(
                            UiGenre(
                                id = "1",
                                name = "Thriller"
                            )
                        )
                    )
                ),

                UiFilter(
                    name = "Genre",
                    isSeclected = true,
                    filterType = UiFilterType.ByGenre(
                        genres = listOf(
                            UiGenre(
                                id = "2",
                                name = "Action"
                            )
                        )
                    ),
                )
            ),
            onFilterClicked = {}
        )
    }
}