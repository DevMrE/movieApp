package com.kmp.movieapp.core.ui.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size

@Composable
fun GridContainer(
    loadNextItems: () -> Unit,
    content: LazyGridScope.() -> Unit,
) {
    val gridState = rememberLazyGridState()

    val shouldLoadMore by remember {
        derivedStateOf {
            val totalItems = gridState.layoutInfo.totalItemsCount
            val lastVisibleIndex = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleIndex >= totalItems - 6 && totalItems > 0
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            loadNextItems()
        }
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive((MaterialTheme.size.defaultCardWidth.value / 1.3).dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        state = gridState,
        contentPadding = PaddingValues(all = MaterialTheme.padding.twelfth),
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.defaultContentPadding,
            alignment = Alignment.CenterVertically
        ),
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.padding.defaultContentPadding,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        content()
    }
}