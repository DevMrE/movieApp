package com.kmp.movieapp.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.browse.mapper.mapToFilter
import com.kmp.movieapp.browse.mapper.toUiGenreList
import com.kmp.movieapp.browse.mapper.toUiMediaCardList
import com.kmp.movieapp.browse.model.UiBrowse
import com.kmp.movieapp.browse.model.filter.UiFilterType
import com.kmp.movieapp.browse.model.filter.UiGenre
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.route.BrowseNavigation
import com.kmp.movieapp.core.util.viewmodel.stateInLazily
import com.kmp.movieapp.discover.domain.usecase.GetDiscoverUseCase
import com.kmp.movieapp.genre.domain.repository.GenreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val getDiscoverUseCase: GetDiscoverUseCase,
    private val navigator: Navigator<BrowseNavigation>,
    genreRepository: GenreRepository
) : ViewModel() {

    private val _browseState = MutableStateFlow(
        value = UiBrowse(
            genreFilter = UiFilterType.Genre(
                genres = genreRepository.movieGenres.value.toUiGenreList()
            )
        )
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val browseState: StateFlow<UiBrowse> = _browseState
        .onEach { data ->
            getDiscoverUseCase(
                page = data.page,
                filter = data.mapToFilter()
            ).collectLatest { data ->
                _browseState.update {
                    it.copy(
                        contentList = data.toUiMediaCardList()
                    )
                }
            }
        }.stateInLazily(_browseState.value)

    fun onAction(action: BrowseAction) {
        when (action) {
            is BrowseAction.OnContentClicked -> navigateToContentDetail(action.uiMediaCard)
            is BrowseAction.OnGenreUpdated -> onUpdateGenre(action.genre)
        }
    }

    private fun onLoadNextPage() {
        viewModelScope.launch {
            _browseState.update {
                it.copy(
                    page = it.page + 1
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

    private fun onUpdateGenre(genre: UiGenre) {
        viewModelScope.launch {
            _browseState.update { state ->
                state.copy(
                    genreFilter = state.genreFilter?.copy(
                        genres = state.genreFilter.genres.map {
                            if (it == genre) it.copy(selected = !it.selected)
                            else it
                        }.sortedWith(comparator = compareByDescending { it.selected })
                    )
                )
            }
        }
    }
}