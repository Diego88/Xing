package com.hiberus.mobile.android.remote.repositories.di

import com.hiberus.mobile.android.remote.BuildConfig
import com.hiberus.mobile.android.remote.repositories.RepositoriesRemoteDataSourceImpl
import com.hiberus.mobile.android.remote.repositories.RepositoriesServiceFactory
import com.hiberus.mobile.android.repository.datasource.repositories.RepositoriesRemoteDataSource
import org.koin.dsl.module

val remoteModule = module {
    single { RepositoriesServiceFactory.makeRepositoriesService(BuildConfig.DEBUG) }
    single<RepositoriesRemoteDataSource> {
        RepositoriesRemoteDataSourceImpl(repositoriesService = get())
    }
}