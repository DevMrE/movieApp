package com.kmp.movieapp.core.util.integer

fun Int?.toHourMinuteString(): String {
    this ?: return "0m"

    val hours = this / 60
    val minutes = this % 60

    return buildString {
        if (hours > 0) append("${hours}h")
        if (minutes > 0) {
            if (isNotEmpty()) append(" ")
            append("${minutes}m")
        }
        if (isEmpty()) append("0m")
    }
}