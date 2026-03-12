package com.kmp.movieapp.app.search

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kmp.movieapp.app.search.action.SearchAction
import com.kmp.movieapp.app.search.destination.SearchScreenDestination
import com.kmp.movieapp.app.search.model.UiSearchState
import com.kmp.movieapp.core.util.boolean.isFalse
import com.kmp.navigation.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val navigation: Navigation
): ViewModel() {

    private val fakeData = listOf(
        "War Machine",
        "Shelter",
        "The Bluff",
        "Whistle",
        "Transformers",
        "Avengers"
    )

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

            val movieList = fakeData.filter { movie ->
                movie.toLowerCase(Locale.current).contains(query.toLowerCase(Locale.current), ignoreCase = true)
            }

            movieList.forEach {
                Logger.i(tag = "Search", messageString = "movie?: $it")
            }

            _searchQueryState.update {
                it.copy(
                    searchResults = movieList
                )
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