package com.kmp.movieapp.content_detail.presentation.formatter

import androidx.compose.ui.text.buildAnnotatedString
import com.kmp.movieapp.content_detail.presentation.model.UiContentDetail
import com.kmp.movieapp.core.util.Constants
import com.kmp.movieapp.core.util.date.formatDateLocalized
import com.kmp.movieapp.core.util.integer.toHourMinuteString
import com.kmp.movieapp.core.util.string.toPrefixedString

internal fun UiContentDetail.toMetaInfoString() = buildAnnotatedString {
    releaseDate?.let { date ->
        val formatedDate = formatDateLocalized(dateString = date, stringFormat = "yyyy")
        append(formatedDate)
    }

    if (runtime != null) {
        append(" ${Constants.BULLET_POINT} ${runtime.toHourMinuteString()}")
    }

    genres?.toPrefixedString(transform = { it.name }, separator = " | ")
        ?.let {
            append(it)
        }
}