package com.mihir.newsbreeze

import android.app.Application
import com.facebook.stetho.Stetho

class NewsBreezeApp:  Application() {
    override fun onCreate() {
        super.onCreate()
        //TODO: Initialize app database
        Stetho.initializeWithDefaults(this)
    }
}