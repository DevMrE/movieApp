package com.kmp.movieapp.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmre.compose.navigation3.helper.Navigator
import com.kmp.movieapp.core.util.mediaCategory.MediaCategory
import com.kmp.movieapp.core.util.viewmodel.stateInSubscribed
import com.kmp.movieapp.discover.domain.GetDiscoverDataUseCase
import com.kmp.movieapp.discover.presentation.mapper.initDiscoverSection
import com.kmp.movieapp.discover.presentation.model.DiscoverUiState
import com.kmp.movieapp.discover.presentation.navigation.DiscoverRoute
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

internal class DiscoverScreenViewModel(
    private val getDiscoverDataUseCase: GetDiscoverDataUseCase,
    private val navigator: Navigator<DiscoverRoute>
) : ViewModel() {

    private val _movieScreenState = MutableStateFlow(DiscoverUiState())

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieScreenState = _movieScreenState
        .flatMapLatest {
            getDiscoverDataUseCase()
        }.map { (trending, popularMovies, popularSeries) ->
            _movieScreenState.updateAndGet {
                it.copy(
                    isLoading = trending.isEmpty() && popularMovies.isEmpty() && popularSeries.isEmpty(),
                    contentSections = initDiscoverSection(
                        trendingList = trending,
                        popularMovies = popularMovies,
                        popularSeries = popularSeries
                    )
                )
            }
        }.stateInSubscribed(initialData = _movieScreenState.value)

    fun onAction(action: DiscoverAction) {
        when (action) {
            is DiscoverAction.OnNavigateToDetailScreen -> navigateToDetailScreen(
                id = action.id,
                mediaCategory = action.mediaCategory
            )

            is DiscoverAction.OnSeeAllClicked -> onSeeAll(action.mediaCategory)
            is DiscoverAction.OnRefresh -> onRefresh()
            is DiscoverAction.NavigateBack -> navigator.navigateBack()
        }
    }

    private fun navigateToDetailScreen(id: String, mediaCategory: MediaCategory) {
        navigator.navigateTo(DiscoverRoute.ContentDetailRoute(id, mediaCategory))
    }

    private fun onSeeAll(mediaCategory: MediaCategory?) {
        if (mediaCategory == null) return
        navigator.navigateTo(DiscoverRoute.SeeAllRoute(mediaCategory))
    }

    private fun onRefresh() {
        viewModelScope.launch {
            _movieScreenState.update {
                DiscoverUiState()
            }

            getDiscoverDataUseCase().collectLatest { (trendings, popularMovies, popularSeries) ->
                _movieScreenState.update {
                    DiscoverUiState(
                        isLoading = trendings.isEmpty() || popularMovies.isEmpty() || popularSeries.isEmpty()
                    )
                }
            }
        }
    }
}