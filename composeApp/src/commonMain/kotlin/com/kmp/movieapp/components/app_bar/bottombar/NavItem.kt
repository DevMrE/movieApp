package com.kmp.movieapp.components.app_bar.bottombar

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import com.kmp.movieapp.core.ui.material.padding
import org.jetbrains.compose.resources.StringResource

@Composable
fun NavItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    labelResource: StringResource?,
    colors: NavigationBarItemColors,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .selectable(
                selected = selected,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(vertical = MaterialTheme.padding.six),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(LocalContentColor provides colors.selectedIconColor) {
            NavIconWithShine(
                selected = selected,
                icon = icon,
                label = labelResource,
            )
        }
    }
}