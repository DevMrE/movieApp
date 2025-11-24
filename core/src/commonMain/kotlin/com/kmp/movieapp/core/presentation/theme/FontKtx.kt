package com.kmp.movieapp.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.kmp.movieapp.core.Res
import com.kmp.movieapp.core.genos_black
import com.kmp.movieapp.core.genos_black_italic
import com.kmp.movieapp.core.genos_bold
import com.kmp.movieapp.core.genos_bold_italic
import com.kmp.movieapp.core.genos_extra_bold
import com.kmp.movieapp.core.genos_extra_bold_italic
import com.kmp.movieapp.core.genos_extra_light
import com.kmp.movieapp.core.genos_extra_light_italic
import com.kmp.movieapp.core.genos_light
import com.kmp.movieapp.core.genos_light_italic
import com.kmp.movieapp.core.genos_medium
import com.kmp.movieapp.core.genos_medium_italic
import com.kmp.movieapp.core.genos_regular
import com.kmp.movieapp.core.genos_semi_bold
import com.kmp.movieapp.core.genos_semi_bold_italic
import com.kmp.movieapp.core.jakarta_bold
import com.kmp.movieapp.core.jakarta_bold_italic
import com.kmp.movieapp.core.jakarta_extra_bold
import com.kmp.movieapp.core.jakarta_extra_bold_italic
import com.kmp.movieapp.core.jakarta_extra_light
import com.kmp.movieapp.core.jakarta_extra_light_italic
import com.kmp.movieapp.core.jakarta_light
import com.kmp.movieapp.core.jakarta_light_italic
import com.kmp.movieapp.core.jakarta_medium
import com.kmp.movieapp.core.jakarta_medium_italic
import com.kmp.movieapp.core.jakarta_regular
import com.kmp.movieapp.core.jakarta_semi_bold
import com.kmp.movieapp.core.jakarta_semi_bold_italic
import org.jetbrains.compose.resources.Font

@Composable
fun JakartaFontFamily() = FontFamily(
    Font(
        resource = Res.font.jakarta_light,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jakarta_light_italic,
        weight = FontWeight.Light,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.jakarta_extra_light,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jakarta_extra_light_italic,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.jakarta_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jakarta_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jakarta_medium_italic,
        weight = FontWeight.Medium,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.jakarta_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jakarta_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.jakarta_semi_bold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jakarta_semi_bold_italic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.jakarta_extra_bold,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.jakarta_extra_bold_italic,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Italic
    )
)

@Composable
fun GenosFontFamily() = FontFamily(
    Font(
        resource = Res.font.genos_light,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.genos_light_italic,
        weight = FontWeight.Light,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.genos_extra_light,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.genos_extra_light_italic,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.genos_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.genos_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.genos_medium_italic,
        weight = FontWeight.Medium,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.genos_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.genos_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.genos_semi_bold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.genos_semi_bold_italic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.genos_extra_bold,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.genos_extra_bold_italic,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.genos_black,
        weight = FontWeight.Black,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.genos_black_italic,
        weight = FontWeight.Black,
        style = FontStyle.Italic
    )
)


