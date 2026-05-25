package com.kmp.movieapp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.home.domain.usecase.GetHomeDataUseCase
import com.kmp.movieapp.home.presentation.action.HomeAction
import com.kmp.movieapp.home.presentation.model.UiHomeData
import com.kmp.movieapp.movie.presentation.mapper.toUiHomeMovieList
import com.kmp.movieapp.series.presentation.mapper.toUiHomeSeriesList
import com.kmp.movieapp.trending.presentation.mapper.toUiHomeTrendingList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

internal class HomeScreenViewModel(
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val navigator: Navigator<HomeNavigation>
) : ViewModel() {

    private val _movieScreenState = MutableStateFlow<UiHomeData?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieScreenState = _movieScreenState
        .flatMapLatest {
            getHomeDataUseCase()
        }.map { (trending, popularMovies, popularSeries) ->
            _movieScreenState.updateAndGet {
                UiHomeData(
                    isLoading = trending.isEmpty() && popularMovies.isEmpty(),
                    trendingList = trending.toUiHomeTrendingList(),
                    popularMovie = popularMovies.toUiHomeMovieList(),
                    popularSeries = popularSeries.toUiHomeSeriesList()
                )
            }
        }.flowOn(Dispatchers.Main.immediate)
        .stateInEagerly(_movieScreenState.value)

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnNavigateToDetailScreen -> navigateToDetailScreen(
                id = action.id,
                mediaCategory = action.mediaCategory
            )

            is HomeAction.OnSeeAllClicked -> onSeeAll(action.mediaCategory)
            is HomeAction.OnRefresh -> onRefresh()
        }
    }

    private fun navigateToDetailScreen(id: String, mediaCategory: MediaCategory) {
        navigator.navigateTo(HomeNavigation.ContentDetailRoute(id, mediaCategory))
    }

    private fun onSeeAll(mediaCategory: MediaCategory?) {
        if (mediaCategory == null) return
        navigator.navigateTo(HomeNavigation.SeeAllRoute(mediaCategory))
    }

    private fun onRefresh() {
        viewModelScope.launch {
            _movieScreenState.update {
                UiHomeData(
                    isLoading = true,
                    trendingList = _movieScreenState.value?.trendingList,
                    popularMovie = _movieScreenState.value?.popularMovie,
                    popularSeries = _movieScreenState.value?.popularSeries
                )
            }

            getHomeDataUseCase().collectLatest { (trendings, popularMovies, popularSeries) ->
                _movieScreenState.update {
                    UiHomeData(
                        isLoading = trendings.isEmpty() && popularMovies.isEmpty(),
                        trendingList = trendings.toUiHomeTrendingList(),
                        popularMovie = trendings.toUiHomeTrendingList(),
                        popularSeries = popularSeries.toUiHomeSeriesList()
                    )
                }
            }
        }
    }
}