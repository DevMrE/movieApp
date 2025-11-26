package com.kmp.movieapp.app.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.kmpnavigation.compose_interface.navigateUp
import com.kmp.movieapp.core.presentation.material.padding
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.app_name
import movieapp.composeapp.generated.resources.back_arrow
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = MaterialTheme.padding.five),
        title = {
            Text(stringResource(Res.string.app_name))
        },
        navigationIcon = {
            Icon(
                imageVector = vectorResource(Res.drawable.back_arrow),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.padding.ten)
                    .navigateUp()
            )
        }
    )
}