package com.kmp.movieapp.core.util

import androidx.activity.ComponentActivity
import java.lang.ref.WeakReference

object ActivityProvider {
    private var _activity: WeakReference<ComponentActivity>? = null

    var activity: ComponentActivity?
        get() = _activity?.get()
        set(value) {
            _activity = value?.let { WeakReference(it) }
        }
}