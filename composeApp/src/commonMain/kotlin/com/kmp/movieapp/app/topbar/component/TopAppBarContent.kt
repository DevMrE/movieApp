package com.kmp.movieapp.app.topbar.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.navigation.compose.navigateUp
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.back_arrow
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarContent(
    title: String,
    navigationIconEnabled: Boolean = false
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.padding.five),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (navigationIconEnabled.isTrue) {
                Icon(
                    imageVector = vectorResource(Res.drawable.back_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.ten)
                        .navigateUp()
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}