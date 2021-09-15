package com.hiberus.mobile.android.xing

import android.app.Application
import com.facebook.stetho.Stetho
import com.hiberus.mobile.android.remote.repositories.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class XingApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@XingApplication)
            modules(
                listOf(
                    remoteModule
                )
            )
        }

        // Initialize Stetho
        Stetho.initializeWithDefaults(this)
    }
}