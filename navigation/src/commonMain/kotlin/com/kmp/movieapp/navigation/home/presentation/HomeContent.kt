package com.kmp.movieapp.navigation.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.navigation.Res
import com.kmp.movieapp.navigation.actors
import com.kmp.movieapp.navigation.home.presentation.action.HomeAction
import com.kmp.movieapp.navigation.home.presentation.content.homeListContent
import com.kmp.movieapp.navigation.home.presentation.model.HomeCategory
import com.kmp.movieapp.navigation.home.presentation.model.UiHomeData
import com.kmp.movieapp.navigation.movies
import com.kmp.movieapp.navigation.series

@Composable
internal fun HomeContent(
    movieScreenState: UiHomeData?,
    onAction: (HomeAction) -> Unit
) {

    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = movieScreenState?.isLoading.isTrue,
        onRefresh = { onAction(HomeAction.OnRefresh) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.thirty),
            contentPadding = PaddingValues(vertical = MaterialTheme.padding.thirty)
        ) {

            movieScreenState?.let { screen ->
                screen.trendingList?.items?.let {
                    homeListContent(
                        title = Res.string.actors,
                        contentList = screen.trendingList.items,
                        homeCategory = HomeCategory.TRENDING,
                        onAction = onAction,
                    )
                }

                screen.popularMovie?.items?.let {
                    homeListContent(
                        title = Res.string.movies,
                        contentList = screen.popularMovie.items,
                        homeCategory = HomeCategory.POPULAR_MOVIES,
                        onAction = onAction
                    )
                }

                screen.popularSeries?.items?.let {
                    homeListContent(
                        title = Res.string.series,
                        contentList = screen.popularSeries.items,
                        homeCategory = HomeCategory.POPULAR_SERIES,
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
