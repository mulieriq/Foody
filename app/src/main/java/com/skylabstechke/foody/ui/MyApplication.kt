package com.skylabstechke.foody.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import leakcanary.LeakCanary

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        LeakCanary.newLeakDisplayActivityIntent()
    }
}