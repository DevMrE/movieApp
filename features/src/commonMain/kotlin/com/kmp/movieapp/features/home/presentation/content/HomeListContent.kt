package com.kmp.movieapp.features.home.presentation.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.material.size
import com.kmp.movieapp.features.Res
import com.kmp.movieapp.features.home.domain.model.HomeCategory
import com.kmp.movieapp.features.home.presentation.action.HomeAction
import com.kmp.movieapp.features.home.presentation.model.UiHomeList
import com.kmp.movieapp.features.movie_category_list_title
import com.kmp.movieapp.features.movie_category_popular
import com.kmp.movieapp.features.movie_category_top_rated
import com.kmp.movieapp.features.see_all
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.homeListContent(
    uiHomeList: UiHomeList?,
    onAction: (HomeAction) -> Unit
) {
    item {
        val categoryTitle = when (uiHomeList?.category) {
            HomeCategory.POPULAR -> stringResource(Res.string.movie_category_popular)
            HomeCategory.TOP_RATED -> stringResource(Res.string.movie_category_top_rated)
            else -> null
        }
        val title: String? = if (categoryTitle != null) {
            stringResource(Res.string.movie_category_list_title, categoryTitle)
        } else null

        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.defaultContentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            title?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.padding.thirty),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    TextButton(
                        onClick = {
                            onAction(HomeAction.OnSeeAllClicked(homeCategory = uiHomeList?.category))
                        },
                    ) {
                        Text(stringResource(Res.string.see_all))
                    }
                }
            }

            val cardSize =
                if (title == null) MaterialTheme.size.moviePosterWidth else MaterialTheme.size.movieCardWidth

            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.thirty),
                contentPadding = PaddingValues(horizontal = MaterialTheme.padding.thirty)
            ) {
                uiHomeList?.movies?.let {
                    mediaCardContent(
                        width = cardSize,
                        movieList = it,
                        onAction = onAction
                    )
                }
            }
        }
    }
}