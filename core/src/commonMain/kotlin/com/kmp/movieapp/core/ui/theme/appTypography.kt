package com.kmp.movieapp.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.sp

@Composable
fun appTypography(): Typography {
    val jakartaFamily = jakartaFontFamily()
    val genosFamily = genosFontFamily()

    return Typography(
        displayLarge = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = jakartaFamily,
            lineHeight = 38.sp,
            lineBreak = LineBreak.Paragraph
        ),
        headlineLarge = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = jakartaFamily,
            lineHeight = 32.sp,
        ),
        headlineMedium = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = jakartaFamily,
        ),
        headlineSmall = TextStyle(
            fontFamily = jakartaFamily,
            fontWeight = FontWeight.Black,
            fontSize = 26.sp,
            lineHeight = 28.sp,
            hyphens = Hyphens.Auto,
            color = MaterialTheme.colorScheme.surface
        ),
        titleMedium = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = jakartaFamily,
            lineHeight = 24.sp,
        ),
        titleLarge = TextStyle(
            fontSize = 38.sp,
            fontWeight = FontWeight.Black,
            fontFamily = jakartaFamily,
            lineHeight = 40.sp,
            color = MaterialTheme.colorScheme.tertiary
        ),
        bodyLarge = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = jakartaFamily,
            lineHeight = 22.sp,
        ),
        bodyMedium = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = jakartaFamily,
            lineHeight = 20.sp,
            color = MaterialTheme.colorScheme.outline
        ),
        bodySmall = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = jakartaFamily,
            lineHeight = 18.sp,
            color = MaterialTheme.colorScheme.outline
        ),
        labelSmall = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            fontFamily = jakartaFamily,
            lineHeight = 18.sp,
            color = MaterialTheme.colorScheme.outline
        )
    )
}