package com.kmp.movieapp.core.util.date

import platform.Foundation.NSDateFormatter
import platform.Foundation.NSDateFormatterMediumStyle
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual fun formatDateLocalized(
    dateString: String,
    stringFormat: String
): String? {
    val formatter = NSDateFormatter().apply {
        dateFormat = stringFormat
    }

    val date = formatter.dateFromString(dateString)

    val outputFormatter = NSDateFormatter().apply {
        locale = NSLocale.currentLocale
        dateStyle = NSDateFormatterMediumStyle
    }

    return if (date == null) ""
    else outputFormatter.stringFromDate(date)
}