package com.kmp.movieapp.homescreen.presentation.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.see_all
import com.kmp.movieapp.core.ui.content.MediaHorizontalList
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.homescreen.presentation.action.HomeAction
import com.kmp.movieapp.homescreen.presentation.model.HomeCategory
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.homeListContent(
    title: StringResource,
    contentList: List<UiMediaCard>,
    homeCategory: HomeCategory,
    onAction: (HomeAction) -> Unit
) {
    val mediaCategory = when (homeCategory) {
        HomeCategory.TRENDING -> MediaCategory.UNKNOWN
        HomeCategory.POPULAR_MOVIES -> MediaCategory.MOVIE
        HomeCategory.POPULAR_SERIES -> MediaCategory.SERIES
        HomeCategory.POPULAR_PEOPLE -> MediaCategory.ACTOR
    }

    item {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.defaultContentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.padding.thirty),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                if (homeCategory != HomeCategory.TRENDING) {
                    TextButton(
                        onClick = {
                            onAction(HomeAction.OnSeeAllClicked(mediaCategory))
                        },
                    ) {
                        Text(stringResource(Res.string.see_all))
                    }
                }
            }

            MediaHorizontalList(
                items = contentList,
                bigCard = homeCategory == HomeCategory.TRENDING,
                onItemClick = { item ->
                    onAction(
                        HomeAction.OnNavigateToDetailScreen(
                            id = item.id,
                            mediaCategory = item.type
                        )
                    )
                },
            )
        }
    }
}