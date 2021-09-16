package com.hiberus.mobile.android.xing

import android.app.Application
import com.facebook.stetho.Stetho
import com.hiberus.mobile.android.domain.repository.di.domainModule
import com.hiberus.mobile.android.local.di.localModule
import com.hiberus.mobile.android.remote.repositories.di.remoteModule
import com.hiberus.mobile.android.repository.di.repositoryModule
import com.hiberus.mobile.android.session.di.sessionModule
import com.hiberus.mobile.android.xing.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class XingApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@XingApplication)
            modules(
                listOf(
                    appModule,
                    domainModule,
                    repositoryModule,
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