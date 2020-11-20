package edu.uoc.android.base

import androidx.multidex.MultiDexApplication
import edu.uoc.android.BuildConfig
import timber.log.Timber

class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}