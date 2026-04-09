package com.kmp.movieapp.content_detail.presentation.component.description

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


internal fun LazyListScope.descriptionTab(
    overview: String?,
) {
    item {
        val selectedTab by remember { mutableStateOf(0) }
        SecondaryTabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier,
            containerColor = TabRowDefaults.primaryContainerColor,
            contentColor = TabRowDefaults.primaryContentColor,
            divider = @Composable { HorizontalDivider() },
            tabs = {
                Tab(
                    onClick = {},
                    selected = false,
                    content = {

                        Column {
                            //Text(stringResource(Res.string.overview_title))

                            overview?.let {
                                Text(it)
                            }
                        }
                    }
                )
            }
        )
    }
}