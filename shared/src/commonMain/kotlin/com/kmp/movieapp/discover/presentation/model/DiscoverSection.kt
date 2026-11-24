package com.kmp.movieapp.discover.presentation.model

import com.kmp.movieapp.core.ui.content.model.UiMediaCard

internal interface DiscoverSection {

    val contentList: List<UiMediaCard>

    data class Trending(
        override val contentList: List<UiMediaCard>
    ) : DiscoverSection

    data class PopularMovies(
        override val contentList: List<UiMediaCard>
    ) : DiscoverSection

    data class PopularSeries(
        override val contentList: List<UiMediaCard>
    ) : DiscoverSection
}