package com.kmp.movieapp.core.language

/**
 * Provides the current language of the application.
 *
 * This method returns the language that is currently active and should be used,
 * for example, in API requests or content localization.
 */
interface LocaleLanguageProvider {
    fun currentLanguage(): String
}