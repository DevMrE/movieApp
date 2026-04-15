package com.kmp.movieapp.content_detail.presentation.mapper

import androidx.compose.ui.text.buildAnnotatedString
import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.presentation.model.ContentDetailUi
import com.kmp.movieapp.core.util.Constants
import com.kmp.movieapp.core.util.boolean.isFalse
import com.kmp.movieapp.core.util.date.formatDateLocalized
import com.kmp.movieapp.core.util.integer.toHourMinuteString
import com.kmp.movieapp.core.util.string.toPrefixedString

fun ContentDetail.toUiData(): ContentDetailUi = ContentDetailUi(
    isLoading = title.isNotEmpty() && posterPath.isNullOrEmpty().isFalse && backdropPath.isNullOrEmpty().isFalse && overview.isNullOrEmpty().isFalse,
    title = title,
    mediaInfo = buildAnnotatedString {
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
    },
    description = overview ?: "",
    posterPath = posterPath ?: "",
    videoThumbnail = backdropPath ?: "",
)