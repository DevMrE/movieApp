package com.kmp.movieapp.core.language

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.kmp.movieapp.core.util.logger.logE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

class AndroidLocaleLanguageProviderImpl(
    context: Context
) : LocaleLanguageProvider, DefaultLifecycleObserver {

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
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        // 1. Try to get language from receiver
        appContext.registerReceiver(
            receiver,
            IntentFilter(Intent.ACTION_LOCALE_CHANGED)
        )

        // 2. Fallback: read language
        val current = Locale.getDefault().language
        if (_language.value != current) {
            _language.update { current }
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        try {
            appContext.unregisterReceiver(receiver)
        } catch (_: Exception) {
            logE<LocaleLanguageProvider>(message = "Error during removing receiver for AndroidLocaleLanguageProvider")
        }
    }
}