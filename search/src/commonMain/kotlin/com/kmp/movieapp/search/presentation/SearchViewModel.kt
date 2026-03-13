package com.kmp.movieapp.search.presentation

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.util.boolean.isFalse
import com.kmp.movieapp.search.domain.repository.SearchRepository
import com.kmp.movieapp.search.presentation.action.SearchAction
import com.kmp.movieapp.search.presentation.destination.SearchScreenDestination
import com.kmp.movieapp.search.presentation.mapper.mapToUiData
import com.kmp.movieapp.search.presentation.model.UiSearchState
import com.kmp.navigation.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val navigation: Navigation,
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _searchQueryState = MutableStateFlow(UiSearchState())
    val searchQueryState = _searchQueryState.asStateFlow()

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnSearchChanged -> updateSearch(action.query)
            is SearchAction.OnSearchActiveChanged -> updateActiveSearch()
        }
    }

    private fun updateSearch(query: String) {
        viewModelScope.launch {
            _searchQueryState.update {
                it.copy(
                    search = query
                )
            }
        }

        viewModelScope.launch {
            searchRepository.getSearchedItems(query).collectLatest { movieList ->
                val filteredList = movieList.filter { movie ->
                    movie.titleInfo.mainTitle.toLowerCase(Locale.current)
                        .contains(query.toLowerCase(Locale.current), ignoreCase = true)
                }.mapToUiData()

                _searchQueryState.update {
                    it.copy(
                        searchResults = filteredList
                    )
                }
            }
        }
        if (query.isNotEmpty()) navigation.navigateTo(SearchScreenDestination)
        else navigation.navigateUp()
    }

    private fun updateActiveSearch() {
        _searchQueryState.update {
            it.copy(
                isSearchActive = !it.isSearchActive
            )
        }

        if (_searchQueryState.value.isSearchActive.isFalse) {
            _searchQueryState.update {
                it.copy(
                    search = "",
                    searchResults = emptyList()
                )
            }
        }
    }
}