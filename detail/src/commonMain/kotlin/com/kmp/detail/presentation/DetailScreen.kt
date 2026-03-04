package com.kmp.detail.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.detail.Res
import com.kmp.movieapp.detail.ic_back_arrow
import com.kmp.navigation.compose.rememberNavigation
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(id: String) {
    val navigation = rememberNavigation()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = vectorResource(Res.drawable.ic_back_arrow), null,
                        modifier = Modifier.clickable(onClick = { navigation.navigateUp() })
                    )
                },
                title = {

                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                contentPadding = PaddingValues(start = MaterialTheme.padding.ten)
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text("Detail: $id")
        }
    }
}
