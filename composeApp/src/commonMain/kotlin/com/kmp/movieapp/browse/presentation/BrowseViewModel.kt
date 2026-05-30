package com.kmp.movieapp.browse.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.browse.domain.model.Browse
import com.kmp.movieapp.browse.domain.usecase.SearchOrDiscoverUseCase
import com.kmp.movieapp.browse.presentation.mapper.mapToFilter
import com.kmp.movieapp.browse.presentation.mapper.mapToUiMediaCardList
import com.kmp.movieapp.browse.presentation.mapper.toUiGenreList
import com.kmp.movieapp.browse.presentation.mapper.toUiMediaCardList
import com.kmp.movieapp.browse.presentation.model.UiBrowse
import com.kmp.movieapp.browse.presentation.model.filter.UiFilterType
import com.kmp.movieapp.browse.presentation.model.filter.UiGenre
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.route.BrowseNavigation
import com.kmp.movieapp.discover.domain.model.Discover
import com.kmp.movieapp.discover.domain.model.Filter
import com.kmp.movieapp.genre.domain.repository.GenreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BrowseViewModel(
    genreRepository: GenreRepository,
    private val navigator: Navigator<BrowseNavigation>,
    private val searchOrDiscoverUseCase: SearchOrDiscoverUseCase
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

    init {
        viewModelScope.launch {
            // Load initial browse list
            updateDiscover(
                page = _browseState.value.page,
                filter = _browseState.value.mapToFilter()
            )
        }
    }

    fun onAction(action: BrowseAction) {
        when (action) {
            is BrowseAction.OnSearchUpdated -> onSearch(action.query)
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
                    search = null,
                    genreFilter = state.genreFilter?.copy(
                        genres = state.genreFilter.genres.map {
                            if (it == genre) it.copy(selected = !it.selected)
                            else it
                        }.sortedWith(comparator = compareByDescending { it.selected })
                    )
                )
            }

            updateDiscover(
                page = _browseState.value.page,
                filter = _browseState.value.mapToFilter()
            )
        }
    }

    private fun onSearch(query: String?) {
        viewModelScope.launch {
            _browseState.update { state ->
                state.copy(
                    search = query,
                    genreFilter = state.genreFilter?.copy(
                        genres = state.genreFilter.genres.map { it.copy(selected = false) }
                    )
                )
            }

            searchOrDiscoverUseCase(
                browse = Browse(page = 1, query = query)
            ).collectLatest { (page, query, search, discover) ->
                _browseState.update {
                    it.copy(
                        page = page,
                        contentList = search?.mapToUiMediaCardList() ?: it.contentList,
                    )
                }
            }
        }
    }

    private fun updateDiscover(page: Int = 1, filter: Filter? = null) {
        viewModelScope.launch {
            searchOrDiscoverUseCase(
                browse = Browse(page = page, discover = Discover(filter = filter))
            ).collectLatest { (page, query, search, discover) ->
                _browseState.update {
                    it.copy(
                        page = page,
                        contentList = discover?.discoverContent?.toUiMediaCardList() ?: emptyList(),
                    )
                }
            }
        }
    }
}