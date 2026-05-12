package com.kmp.movieapp.discover.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.discover.presentation.model.UiFilter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterComponent(
    filters: List<UiFilter>?,
    onFilterClicked: (UiFilter) -> Unit
) {
    Row(
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
                    selectedContainerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}