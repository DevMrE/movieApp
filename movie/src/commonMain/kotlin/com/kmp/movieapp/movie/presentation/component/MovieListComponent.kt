package com.kmp.movieapp.movie.presentation.component

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
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.model.UiMovieList
import org.jetbrains.compose.resources.stringResource

fun LazyListScope.movieListComponent(
    uiMovieList: UiMovieList,
    onAction: (MovieAction) -> Unit
) {
    item {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.ten),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            uiMovieList.titleRes?.let { titleRes ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.padding.thirty),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = stringResource(titleRes),
                        style = MaterialTheme.typography.headlineMedium
                    )

                    TextButton(
                        onClick = {

                        },
                    ) {
                        Text("See All")
                    }
                }
            }

            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.thirty),
                contentPadding = PaddingValues(horizontal = MaterialTheme.padding.thirty)
            ) {

                movieCardComponent(
                    bigCard = uiMovieList.titleRes == null,
                    movieList = uiMovieList.movies,
                    onAction = onAction
                )
            }
        }
    }
}