package com.kmp.movieapp.components.topbar.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.ic_arrow_left
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAppBarComponent(
    title: String?,
    onBackClick: (() -> Unit)? = null
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Column {
        CenterAlignedTopAppBar(
            title = {
                title?.let {
                    LogoWithTitle(title = it)
                }
            },
            navigationIcon = {
                AnimatedVisibility(
                    visible = onBackClick != null,
                    enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
                    exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start)
                ) {
                    NavigationBackIcon(
                        navigateBack = {
                            onBackClick?.let { click -> click() }
                        },
                    )
                }
            },
            scrollBehavior = scrollBehavior,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                subtitleContentColor = MaterialTheme.colorScheme.primary,
            )
        )
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.surfaceContainer)
    }
}

@Composable
private fun NavigationBackIcon(
    navigateBack: () -> Unit
) {
    IconButton(
        onClick = navigateBack,
        modifier = Modifier.animateContentSize()
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_arrow_left),
            contentDescription = null
        )
    }
}

@Composable
private fun LogoWithTitle(title: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        AppLogo()

        PageTitle(title)
    }
}

@Composable
private fun RowScope.AppLogo() {
    Text(
        modifier = Modifier.alignBy(FirstBaseline),
        text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append("Vyn")
            }

            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append("ora")
            }
        }
    )
}

@Composable
private fun RowScope.PageTitle(title: String?) {
    Box(
        modifier = Modifier
            .alignBy(LastBaseline)
            .padding(end = MaterialTheme.padding.sixteen),
        contentAlignment = Alignment.BottomEnd
    ) {
        title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }
    }
}

@PreviewScreenSizes
@Composable
private fun TopAppBarComponentPreview() {
    AppTheme {
        TopAppBarComponent(
            title = "Discover"
        ) {}
    }
}