package com.kmp.movieapp.home.presentation.component.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.see_all
import com.kmp.movieapp.home.presentation.model.HomeCategory
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