package com.kmp.movieapp.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.features.Res
import com.kmp.movieapp.features.home.presentation.action.HomeAction
import com.kmp.movieapp.features.home.presentation.content.homeListContent
import com.kmp.movieapp.features.home.presentation.model.HomeCategory
import com.kmp.movieapp.features.home_popular_movies_title
import com.kmp.movieapp.features.home_popular_series_title
import com.kmp.movieapp.features.home_trending_title
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeContent() {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val movieScreenState by viewModel.movieScreenState.collectAsStateWithLifecycle()

    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = movieScreenState?.isLoading.isTrue,
        onRefresh = { viewModel.onAction(HomeAction.OnRefresh) }
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
                        title = Res.string.home_trending_title,
                        contentList = screen.trendingList.items,
                        homeCategory = HomeCategory.TRENDING,
                        onAction = viewModel::onAction,
                    )
                }

                screen.popularMovie?.items?.let {
                    homeListContent(
                        title = Res.string.home_popular_movies_title,
                        contentList = screen.popularMovie.items,
                        homeCategory = HomeCategory.POPULAR_MOVIES,
                        onAction = viewModel::onAction
                    )
                }

                screen.popularSeries?.items?.let {
                    homeListContent(
                        title = Res.string.home_popular_series_title,
                        contentList = screen.popularSeries.items,
                        homeCategory = HomeCategory.POPULAR_SERIES,
                        onAction = viewModel::onAction
                    )
                }
            }

            item {
                Spacer(Modifier.height(MaterialTheme.size.bottomBarHeight))
            }
        }
    }
}
