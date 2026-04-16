package com.kmp.movieapp.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.core.ui.navigation.MediaDetailDestination
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.features.home.domain.model.HomeCategory
import com.kmp.movieapp.features.home.domain.usecase.GetMoviesForCategoryUseCase
import com.kmp.movieapp.features.home.presentation.action.HomeAction
import com.kmp.movieapp.features.home.presentation.destination.HomeMediaCategoryListDestination
import com.kmp.movieapp.features.home.presentation.mapper.toUiHomeList
import com.kmp.movieapp.features.home.presentation.model.UiMovieScreen
import com.kmp.navigation.Navigation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

internal class HomeScreenViewModel(
    private val navigation: Navigation,
    private val getMoviesForCategoryUseCase: GetMoviesForCategoryUseCase
) : ViewModel() {

    private val _movieScreenState = MutableStateFlow<UiMovieScreen?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieScreenState = _movieScreenState
        .flatMapLatest {
            getMoviesForCategoryUseCase()
        }.map { (popular, topRated, nowPlaying) ->
            _movieScreenState.updateAndGet {
                UiMovieScreen(
                    isLoading = popular == null && topRated == null && nowPlaying == null,
                    nowPlaying = nowPlaying?.toUiHomeList(category = HomeCategory.NOW_PLAYING),
                    popularMovie = popular?.toUiHomeList(category = HomeCategory.POPULAR),
                    topRatedMovies = topRated?.toUiHomeList(category = HomeCategory.TOP_RATED)
                )
            }
        }.stateInEagerly(_movieScreenState.value)

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
                UiMovieScreen(
                    isLoading = true,
                    nowPlaying = _movieScreenState.value?.nowPlaying,
                    popularMovie = _movieScreenState.value?.popularMovie,
                    topRatedMovies = _movieScreenState.value?.topRatedMovies,
                )
            }

            getMoviesForCategoryUseCase().collectLatest { (popular, topRated, nowPlaying) ->
                _movieScreenState.update {
                    UiMovieScreen(
                        isLoading = popular == null && topRated == null && nowPlaying == null,
                        nowPlaying = nowPlaying?.toUiHomeList(category = HomeCategory.NOW_PLAYING),
                        popularMovie = popular?.toUiHomeList(category = HomeCategory.POPULAR),
                        topRatedMovies = topRated?.toUiHomeList(category = HomeCategory.TOP_RATED)
                    )
                }
            }
        }
    }
}