package com.kmp.movieapp.androidapp

import android.app.Service
import android.content.Intent
import android.os.IBinder

class CameraService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}