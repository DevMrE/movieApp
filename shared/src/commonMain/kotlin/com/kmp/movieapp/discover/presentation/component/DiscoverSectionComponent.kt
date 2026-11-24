package com.kmp.movieapp.discover.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.discover.presentation.HomeAction
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.discoverSection(
    title: StringResource,
    mediaCategory: MediaCategory,
    contentList: List<UiMediaCard>,
    onAction: (HomeAction) -> Unit
) {

    item {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.padding.defaultContentPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                SeeAllButton(
                    mediaCategory = mediaCategory,
                    onClick = onAction
                )
            }

            DiscoverHorizontalListComponent(
                items = contentList,
                bigCard = mediaCategory == MediaCategory.UNKNOWN,
                onItemClick = { item ->
                    onAction(
                        HomeAction.OnNavigateToDetailScreen(
                            id = item.id,
                            mediaCategory = item.type
                        )
                    )
                }
            )
        }
    }
}