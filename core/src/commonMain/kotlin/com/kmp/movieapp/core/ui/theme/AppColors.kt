package com.kmp.movieapp.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class VynoraColors(
    val background: Color,
    val surface: Color,
    val surfaceElevated: Color,
    val accent: Color,
    val accentContainer: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val posterScrim: Color,
    val border: Color,
    val textAccent: Color,
)

val LightColors = VynoraColors(
    background = Color(0xFFFAF8FF),
    surface = Color(0xFFFFFFFF),
    surfaceElevated = Color(0xFFF1F5F9),
    accent = Color(0xFF855300),
    accentContainer = Color(0xFFF59E0B),
    textPrimary = Color(0xFF191625),
    textSecondary = Color(0xFF625E70),
    textAccent = Color(0xFFFFC174),
    posterScrim = Color(0x66000000),
    border = Color(0xFF625E70).copy(alpha = 0.3f),
)

val DarkColors = VynoraColors(
    background = Color(0xFF0F131C),
    surface = Color(0xFF1C2028),
    surfaceElevated = Color(0xFF262A33),
    accent = Color(0xFFFFC174),
    accentContainer = Color(0xFFF59E0B),
    textPrimary = Color(0xFFDFE2EE),
    textSecondary = Color(0xFFD8C3AD),
    textAccent = Color(0x99000000),
    posterScrim = Color(0x66000000),
    border = Color(0xFFD8C3AD).copy(alpha = 0.3f),
)

val LocalVynoraColors = staticCompositionLocalOf {
    DarkColors
}

val MaterialTheme.vynoraColors: VynoraColors
    @Composable
    @ReadOnlyComposable
    get() = LocalVynoraColors.current

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF855300),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFF59E0B),
    onPrimaryContainer = Color(0xFF2A1700),

    secondary = Color(0xFFB52619),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFF5C47),
    onSecondaryContainer = Color(0xFF600100),

    tertiary = Color(0xFF904D00),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFF9837),
    onTertiaryContainer = Color(0xFF6A3700),

    background = Color(0xFFF8FAFC),
    onBackground = Color(0xFF0F172A),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFDAE2FD),
    onSurfaceVariant = Color(0xFF475569),

    surfaceTint = Color(0xFF855300),
    inverseSurface = Color(0xFF283044),
    inverseOnSurface = Color(0xFFEEF0FF),
    inversePrimary = Color(0xFFFFB95F),

    surfaceDim = Color(0xFFD2D9F4),
    surfaceBright = Color(0xFFFAF8FF),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFFFFFFF),
    surfaceContainer = Color(0xFFF8FAFC),
    surfaceContainerHigh = Color(0xFFF1F5F9),
    surfaceContainerHighest = Color(0xFFE2E8F0),

    outline = Color(0xFF867461),
    outlineVariant = Color(0xFFE2E8F0),

    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF93000A),

    primaryFixed = Color(0xFFFFDDB8),
    primaryFixedDim = Color(0xFFFFB95F),
    onPrimaryFixed = Color(0xFF2A1700),
    onPrimaryFixedVariant = Color(0xFF653E00),

    secondaryFixed = Color(0xFFFFDAD4),
    secondaryFixedDim = Color(0xFFFFB4A8),
    onSecondaryFixed = Color(0xFF410000),
    onSecondaryFixedVariant = Color(0xFF920702),

    tertiaryFixed = Color(0xFFFFDCC3),
    tertiaryFixedDim = Color(0xFFFFB77D),
    onTertiaryFixed = Color(0xFF2F1500),
    onTertiaryFixedVariant = Color(0xFF6E3900),
)


val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFC174),
    onPrimary = Color(0xFF472A00),
    primaryContainer = Color(0xFFF59E0B),
    onPrimaryContainer = Color(0xFF613B00),

    secondary = Color(0xFFFFB2B7),
    onSecondary = Color(0xFF67001B),
    secondaryContainer = Color(0xFFB50036),
    onSecondaryContainer = Color(0xFFFFC2C4),

    tertiary = Color(0xFF8ED5FF),
    onTertiary = Color(0xFF00354A),
    tertiaryContainer = Color(0xFF38BDF8),
    onTertiaryContainer = Color(0xFF004965),

    background = Color(0xFF0F131C),
    onBackground = Color(0xFFDFE2EE),

    surface = Color(0xFF1C2028),
    onSurface = Color(0xFFDFE2EE),
    surfaceVariant = Color(0xFF31353E),
    onSurfaceVariant = Color(0xFFD8C3AD),

    surfaceTint = Color(0xFFFFB95F),
    inverseSurface = Color(0xFFDFE2EE),
    inverseOnSurface = Color(0xFF2C3039),
    inversePrimary = Color(0xFF855300),

    surfaceDim = Color(0xFF0F131C),
    surfaceBright = Color(0xFF353942),
    surfaceContainerLowest = Color(0xFF0A0E16),
    surfaceContainerLow = Color(0xFF181C24),
    surfaceContainer = Color(0xFF1C2028),
    surfaceContainerHigh = Color(0xFF262A33),
    surfaceContainerHighest = Color(0xFF31353E),

    outline = Color(0xFFA08E7A),
    outlineVariant = Color(0xFF534434),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    primaryFixed = Color(0xFFFFDDB8),
    primaryFixedDim = Color(0xFFFFB95F),
    onPrimaryFixed = Color(0xFF2A1700),
    onPrimaryFixedVariant = Color(0xFF653E00),

    secondaryFixed = Color(0xFFFFDADB),
    secondaryFixedDim = Color(0xFFFFB2B7),
    onSecondaryFixed = Color(0xFF40000D),
    onSecondaryFixedVariant = Color(0xFF92002A),

    tertiaryFixed = Color(0xFFC4E7FF),
    tertiaryFixedDim = Color(0xFF7BD0FF),
    onTertiaryFixed = Color(0xFF001E2C),
    onTertiaryFixedVariant = Color(0xFF004C69),
)
