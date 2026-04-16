package com.kmp.movieapp.core.language

import kotlinx.coroutines.flow.MutableStateFlow
import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

class IosLocaleLanguageProviderImpl : LocaleLanguageProvider {

    private fun getSystemLanguage() : String {
        val lang = NSLocale.preferredLanguages.firstOrNull() as? String
        return lang?.substringBefore("-") ?: "en"
    }

    private val _language = MutableStateFlow(getSystemLanguage())

    override fun currentLanguage(): String = _language.value

}