package com.rbnb.userdirectory

import android.app.Application
import com.rbnb.userdirectory.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class UserDirectoryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}