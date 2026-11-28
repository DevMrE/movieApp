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
            lineHeight = 26.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = jakartaFamily,
            fontWeight = FontWeight.Black,
            fontSize = 26.sp,
            lineHeight = 28.sp,
            hyphens = Hyphens.Auto,
            color = MaterialTheme.colorScheme.surface
        ),
        titleLarge = TextStyle(
            fontSize = 38.sp,
            fontWeight = FontWeight.Black,
            fontFamily = jakartaFamily,
            lineHeight = 40.sp,
        ),
        titleMedium = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = jakartaFamily,
            lineHeight = 34.sp,
        ),
        titleSmall = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = jakartaFamily,
            lineHeight = 30.sp,
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
        ),
        bodySmall = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = jakartaFamily,
            lineHeight = 18.sp,
        ),
        labelSmall = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            fontFamily = jakartaFamily,
            lineHeight = 14.sp,
        ),
        labelMedium = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = jakartaFamily,
            lineHeight = 16.sp,
        ),
        labelLarge = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = jakartaFamily,
            lineHeight = 18.sp,
        ),
    )
}