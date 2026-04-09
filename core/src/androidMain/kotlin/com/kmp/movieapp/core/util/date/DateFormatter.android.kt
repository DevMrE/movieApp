package com.kmp.movieapp.core.util.date

import co.touchlab.kermit.Logger
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

actual fun formatDateLocalized(dateString: String, stringFormat: String): String? {
    try {
        val date = LocalDate.parse(dateString)
        val formatter = DateTimeFormatter.ofPattern(stringFormat, Locale.getDefault())
        return date.format(formatter)
    } catch (e: RuntimeException) {
        Logger.e(tag = "DateFormatter", throwable = e, messageString = "Error during date formatting.")
        return null
    }
}