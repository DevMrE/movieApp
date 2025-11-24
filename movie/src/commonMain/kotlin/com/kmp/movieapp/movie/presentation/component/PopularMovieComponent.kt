package com.kmp.movieapp.movie.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.movie.presentation.action.MovieAction
import com.kmp.movieapp.movie.presentation.model.UiMovie

@Composable
fun PopularMovieComponent(
    movieList: List<UiMovie>,
    onAction: (MovieAction) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.ten),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = MaterialTheme.padding.thirty),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text("Popular Movies", style = MaterialTheme.typography.headlineMedium)

            TextButton(
                onClick = {

                },
            ) {
                Text("See All")
            }
        }

        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.thirty),
            contentPadding = PaddingValues(horizontal = MaterialTheme.padding.thirty)
        ) {

            movieCardComponent(movieList)
        }
    }
}