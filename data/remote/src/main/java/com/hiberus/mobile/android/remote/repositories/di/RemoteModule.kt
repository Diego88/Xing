package com.hiberus.mobile.android.remote.repositories.di

import com.hiberus.mobile.android.data.datasource.repositories.RepositoriesRemoteDataSource
import com.hiberus.mobile.android.remote.BuildConfig
import com.hiberus.mobile.android.remote.repositories.RepositoriesRemoteDataSourceImpl
import com.hiberus.mobile.android.remote.repositories.RepositoriesServiceFactory
import org.koin.dsl.module

val remoteModule = module {
    single { RepositoriesServiceFactory.makeRepositoriesService(BuildConfig.DEBUG) }
    single<RepositoriesRemoteDataSource> {
        RepositoriesRemoteDataSourceImpl(repositoriesService = get())
    }
}