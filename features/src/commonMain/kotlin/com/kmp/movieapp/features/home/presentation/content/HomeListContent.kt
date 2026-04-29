package com.kmp.movieapp.features.home.presentation.content

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
import com.kmp.movieapp.core.ui.content.MediaHorizontalList
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.features.Res
import com.kmp.movieapp.features.home.presentation.action.HomeAction
import com.kmp.movieapp.features.home.presentation.model.HomeCategory
import com.kmp.movieapp.features.see_all
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.homeListContent(
    title: StringResource,
    contentList: List<UiMediaCard>,
    homeCategory: HomeCategory,
    onAction: (HomeAction) -> Unit
) {
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

                TextButton(
                    onClick = {
                        onAction(HomeAction.OnSeeAllClicked(homeCategory))
                    },
                ) {
                    Text(stringResource(Res.string.see_all))
                }
            }

            MediaHorizontalList(
                items = contentList,
                onItemClick = { item ->
                    onAction(
                        HomeAction.OnNavigateToDetailScreen(
                            id = item.id,
                            contentDetailType = item.type
                        )
                    )
                },
            )
        }
    }
}