package com.hiberus.mobile.android.xing

import android.app.Application
import com.facebook.stetho.Stetho
import com.hiberus.mobile.android.local.di.localModule
import com.hiberus.mobile.android.remote.repositories.di.remoteModule
import com.hiberus.mobile.android.session.di.sessionModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class XingApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@XingApplication)
            modules(
                listOf(
                    sessionModule,
                    localModule,
                    remoteModule
                )
            )
        }

        // Initialize Stetho
        Stetho.initializeWithDefaults(this)
    }
}