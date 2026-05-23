package com.kmp.movieapp.navigation.home.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.kmp.movieapp.navigation.Res
import com.kmp.movieapp.navigation.home.presentation.model.HomeCategory
import com.kmp.movieapp.navigation.see_all
import org.jetbrains.compose.resources.stringResource

@Composable
fun SeeAllButton(
    homeCategory: HomeCategory,
    onClick: () -> Unit
) {
    if (homeCategory != HomeCategory.TRENDING) {
        TextButton(
            onClick = onClick,
        ) {
            Text(stringResource(Res.string.see_all), color = MaterialTheme.colorScheme.primary)
        }
    }
}