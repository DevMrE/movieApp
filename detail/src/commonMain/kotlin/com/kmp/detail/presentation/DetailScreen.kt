package com.kmp.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(movieId: Int) {

    Column {
        Text("Title")

        Text(movieId.toString())
    }
}