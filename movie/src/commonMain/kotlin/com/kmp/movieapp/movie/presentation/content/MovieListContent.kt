package com.kmp.movieapp.movie.presentation.content

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
import com.kmp.movieapp.movie.Res
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.movie_category_list_title
import com.kmp.movieapp.movie.movie_category_popular
import com.kmp.movieapp.movie.movie_category_top_rated
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.model.UiMovieList
import com.kmp.movieapp.movie.see_all
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.movieListContent(
    uiMovieList: UiMovieList?,
    onAction: (MovieAction) -> Unit
) {
    item {
        val categoryTitle = when (uiMovieList?.category) {
            MovieCategory.POPULAR -> stringResource(Res.string.movie_category_popular)
            MovieCategory.TOP_RATED -> stringResource(Res.string.movie_category_top_rated)
            else -> null
        }
        val title: String? = if (categoryTitle != null) {
            stringResource(Res.string.movie_category_list_title, categoryTitle)
        } else null

        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.ten),
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
                            onAction(MovieAction.OnSeeAllClicked(movieCategory = uiMovieList?.category))
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
                uiMovieList?.movies?.let {
                    movieCardContent(
                        width = cardSize,
                        movieList = it,
                        onAction = onAction
                    )
                }
            }
        }
    }
}