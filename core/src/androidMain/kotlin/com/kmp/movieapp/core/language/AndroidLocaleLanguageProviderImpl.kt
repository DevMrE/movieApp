package com.kmp.movieapp.core.language

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.kmp.movieapp.core.util.logger.logE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

class AndroidLocaleLanguageProviderImpl(
    private val context: Context
) : LocaleLanguageProvider {

    private val appContext = context.applicationContext

    private val _language = MutableStateFlow(Locale.getDefault().language)

    override fun currentLanguage(): String = _language.value

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val newLang = Locale.getDefault().language
            if (_language.value != newLang) {
                _language.update { newLang }
            }
        }
    }

    init {
        appContext.registerReceiver(
            receiver,
            IntentFilter(Intent.ACTION_LOCALE_CHANGED)
        )
    }

    fun dispose() {
        try {
            appContext.unregisterReceiver(receiver)
        } catch (_: Exception) {
            logE<LocaleLanguageProvider>(message = "Error during removing receiver for AndroidLocaleLanguageProvider")
        }
    }
}