package com.kmp.movieapp.app.topbar.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.navigation.compose.rememberNavigation
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.ic_back_arrow
import movieapp.composeapp.generated.resources.ic_movie
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarContent(
    title: String,
    showBackButton: Boolean = false,
) {
    val navigation = rememberNavigation()

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.five)
            ) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_movie),
                    contentDescription = null
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { navigation.navigateUp() }) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.ic_back_arrow),
                        contentDescription = null,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}