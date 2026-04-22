package com.kmp.movieapp.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.core.ui.navigation.MediaDetailDestination
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.features.home.presentation.action.HomeAction
import com.kmp.movieapp.features.home.presentation.destination.HomeMediaCategoryListDestination
import com.kmp.movieapp.features.home.presentation.mapper.toUiHomeMovieList
import com.kmp.movieapp.features.home.presentation.mapper.toUiHomeTrendingList
import com.kmp.movieapp.features.home.presentation.model.HomeCategory
import com.kmp.movieapp.features.home.presentation.model.UiHomeData
import com.kmp.movieapp.features.movie.data.domain.usecase.GetHomeDataUseCase
import com.kmp.navigation.Navigation
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
    private val navigation: Navigation,
    private val getHomeDataUseCase: GetHomeDataUseCase
) : ViewModel() {

    private val _movieScreenState = MutableStateFlow<UiHomeData?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieScreenState = _movieScreenState
        .flatMapLatest {
            getHomeDataUseCase()
        }.map { (trending, popularMovies) ->
            _movieScreenState.updateAndGet {
                UiHomeData(
                    isLoading = trending.isEmpty() && popularMovies == null,
                    trendingList = trending.toUiHomeTrendingList(),
                    popularMovie = popularMovies?.toUiHomeMovieList(),
                    popularSeries = null
                )
            }
        }.flowOn(Dispatchers.Main.immediate)
        .stateInEagerly(_movieScreenState.value)

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnNavigateToDetailScreen -> navigateToDetailScreen(action.id)
            is HomeAction.OnSeeAllClicked -> onSeeAll(action.homeCategory)
            is HomeAction.OnRefresh -> onRefresh()
        }
    }

    private fun navigateToDetailScreen(id: String) {
        navigation.navigateTo(
            MediaDetailDestination(
                id = id,
                contentDetailType = ContentDetailType.MOVIE
            )
        )
    }

    private fun onSeeAll(homeCategory: HomeCategory?) {
        if (homeCategory == null) return
        navigation.navigateTo(destination = HomeMediaCategoryListDestination(homeCategory))
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

            getHomeDataUseCase().collectLatest { (trendings, popularMovies) ->
                _movieScreenState.update {
                    UiHomeData(
                        isLoading = trendings.isEmpty() && popularMovies == null,
                        trendingList = trendings.toUiHomeTrendingList(),
                        popularMovie = trendings.toUiHomeTrendingList(),
                        popularSeries = null
                    )
                }
            }
        }
    }
}