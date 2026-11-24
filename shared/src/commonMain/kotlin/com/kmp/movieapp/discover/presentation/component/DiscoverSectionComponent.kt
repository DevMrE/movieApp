package com.kmp.movieapp.discover.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.discover.presentation.DiscoverAction
import com.kmp.movieapp.discover.presentation.model.DiscoverSection
import com.kmp.movieapp.model.UiMediaCard
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.popular_movies_title
import com.kmp.movieapp.shared.popular_series_title
import com.kmp.movieapp.shared.trending_title
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun DiscoverSectionComponent(
    refresh: Boolean,
    contentList: Set<DiscoverSection>?,
    paddingWithoutBottom: PaddingValues,
    onAction: (DiscoverAction) -> Unit
) {
    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingWithoutBottom),
        isRefreshing = refresh,
        onRefresh = { onAction(DiscoverAction.OnRefresh) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.thirty),
            contentPadding = PaddingValues(vertical = MaterialTheme.padding.thirty)
        ) {
            contentList?.forEach { section ->
                when (section) {
                    is DiscoverSection.Trending ->
                        discoverSection(
                            title = Res.string.trending_title,
                            mediaCategory = MediaCategory.UNKNOWN,
                            contentList = section.contentList,
                            onAction = onAction
                        )

                    is DiscoverSection.PopularMovies ->
                        discoverSection(
                            title = Res.string.popular_movies_title,
                            mediaCategory = MediaCategory.MOVIE,
                            contentList = section.contentList,
                            onAction = onAction
                        )

                    is DiscoverSection.PopularSeries ->
                        discoverSection(
                            title = Res.string.popular_series_title,
                            mediaCategory = MediaCategory.SERIES,
                            contentList = section.contentList,
                            onAction = onAction
                        )
                }
            }

            item {
                Spacer(Modifier.height(MaterialTheme.size.bottomBarHeight))
            }
        }
    }
}

private fun LazyListScope.discoverSection(
    title: StringResource,
    mediaCategory: MediaCategory,
    contentList: List<UiMediaCard>,
    onAction: (DiscoverAction) -> Unit
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
                        DiscoverAction.OnNavigateToDetailScreen(
                            id = item.id,
                            mediaCategory = mediaCategory
                        )
                    )
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun DiscoverScreenPreview() {
    val contentList = setOf(
        DiscoverSection.Trending(
            contentList = (0..100).map {
                UiMediaCard(
                    id = "",
                    title = "Series $it",
                    posterPath = null,
                    backdropPath = null,
                    voteAverage = null,
                    type = MediaCategory.SERIES
                )
            }
        ),
        DiscoverSection.PopularMovies(
            contentList = (0..100).map {
                UiMediaCard(
                    id = "",
                    title = "Series $it",
                    posterPath = null,
                    backdropPath = null,
                    voteAverage = 8.6,
                    type = MediaCategory.SERIES
                )
            }
        ),
        DiscoverSection.PopularSeries(
            contentList = (0..100).map {
                UiMediaCard(
                    id = "",
                    title = "Series $it",
                    posterPath = null,
                    backdropPath = null,
                    voteAverage = 7.4,
                    type = MediaCategory.SERIES
                )
            }
        ),
    )

    AppTheme {
        Surface {
            DiscoverSectionComponent(
                refresh = false,
                contentList = contentList,
                paddingWithoutBottom = PaddingValues(),
                onAction = {}
            )
        }
    }
}