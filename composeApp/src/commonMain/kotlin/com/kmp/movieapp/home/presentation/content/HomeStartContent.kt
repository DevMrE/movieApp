package com.kmp.movieapp.home.presentation.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.components.app_bar.topbar.component.TopAppBarContent
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.app_name
import com.kmp.movieapp.composeApp.popular_movies_title
import com.kmp.movieapp.composeApp.popular_series_title
import com.kmp.movieapp.composeApp.trending_title
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.home.presentation.HomeScreenViewModel
import com.kmp.movieapp.home.presentation.action.HomeAction
import com.kmp.movieapp.home.presentation.model.HomeCategory
import com.kmp.movieapp.home.presentation.model.HomeCategory.TRENDING
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeStartContent() {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val movieScreenState by viewModel.movieScreenState.collectAsStateWithLifecycle()
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarContent(
                title = stringResource(Res.string.app_name),
                scrollBehavior = topAppBarScrollBehavior
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        val layoutDir = LocalLayoutDirection.current
        val paddingWithoutBottom = PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDir),
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(layoutDir),
            bottom = 0.dp
        )

        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingWithoutBottom),
            isRefreshing = movieScreenState?.isLoading.isTrue,
            onRefresh = { viewModel.onAction(HomeAction.OnRefresh) }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.thirty),
                contentPadding = PaddingValues(vertical = MaterialTheme.padding.thirty)
            ) {

                movieScreenState.let { screen ->
                    screen?.trendingList?.items?.let {
                        homeListContent(
                            title = Res.string.trending_title,
                            contentList = screen.trendingList.items,
                            homeCategory = TRENDING,
                            onAction = viewModel::onAction,
                        )
                    }

                    screen?.popularMovie?.items?.let {
                        homeListContent(
                            title = Res.string.popular_movies_title,
                            contentList = screen.popularMovie.items,
                            homeCategory = HomeCategory.POPULAR_MOVIES,
                            onAction = viewModel::onAction
                        )
                    }

                    screen?.popularSeries?.items?.let {
                        homeListContent(
                            title = Res.string.popular_series_title,
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
}
