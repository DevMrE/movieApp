package com.kmp.movieapp.movie.presentation.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.movieapp.movie.Res
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.model.UiMovieList
import com.kmp.movieapp.movie.see_all
import org.jetbrains.compose.resources.stringResource

fun LazyListScope.movieListContent(
    uiMovieList: UiMovieList,
    onAction: (MovieAction) -> Unit
) {
    item {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.ten),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            uiMovieList.title?.let {
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
                            onAction(MovieAction.OnSeeAllClicked)
                        },
                    ) {
                        Text(stringResource(Res.string.see_all))
                    }
                }
            }

            val cardSize = if (uiMovieList.title == null) MaterialTheme.size.moviePosterWidth else MaterialTheme.size.movieCardWidth

            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.thirty),
                contentPadding = PaddingValues(horizontal = MaterialTheme.padding.thirty)
            ) {

                movieCardContent(
                    width = cardSize,
                    movieList = uiMovieList.movies,
                    onAction = onAction
                )
            }
        }
    }
}