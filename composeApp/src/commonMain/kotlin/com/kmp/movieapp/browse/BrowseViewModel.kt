package com.kmp.movieapp.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.browse.mapper.toUiDiscoverList
import com.kmp.movieapp.browse.model.UiDiscover
import com.kmp.movieapp.browse.model.UiFilter
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.route.BrowseNavigation
import com.kmp.movieapp.core.util.viewmodel.stateInLazily
import com.kmp.movieapp.discover.domain.usecase.GetDiscoverUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val getDiscoverUseCase: GetDiscoverUseCase,
    private val navigator: Navigator<BrowseNavigation>
) : ViewModel() {

    private val _browseState = MutableStateFlow<UiDiscover?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val browseState: StateFlow<UiDiscover?> = _browseState
        .flatMapLatest {
            getDiscoverUseCase(page = _browseState.value?.page ?: 1)
        }.map { data ->
            _browseState.value ?: _browseState.updateAndGet {
                data.toUiDiscoverList()
            }
        }
        .stateInLazily(_browseState.value)

    fun onAction(action: BrowseAction) {
        when (action) {
            is BrowseAction.OnFilterClicked -> onUpdateFilter(action.uiFilter)
            is BrowseAction.OnContentClicked -> navigateToContentDetail(action.uiMediaCard)
        }
    }

    private fun onLoadNextPage() {
        viewModelScope.launch {
            _browseState.update {
                it?.copy(
                    page = it.page + 1
                )
            }
        }
    }

    private fun onUpdateFilter(uiFilter: UiFilter) {
        viewModelScope.launch {
            _browseState.update { state ->
                state?.copy(
                    filter = state.filter
                        .filter { it == uiFilter }
                        .map { filter ->
                            filter.copy(isSeclected = !filter.isSeclected)
                        }
                )
            }
        }
    }


    private fun navigateToContentDetail(uiMediaCard: UiMediaCard) {
        logI(message = "Navigate with id: $uiMediaCard")
        navigator.navigateTo(
            route = BrowseNavigation.ContentDetailRoute(
                id = uiMediaCard.id,
                mediaCategory = uiMediaCard.type
            )
        )
    }

}