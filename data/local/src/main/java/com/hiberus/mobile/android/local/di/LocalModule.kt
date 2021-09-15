package com.hiberus.mobile.android.local.di

import com.hiberus.mobile.android.data.datasource.repositories.RepositoriesLocalDataSource
import com.hiberus.mobile.android.local.RepositoriesDatabase
import com.hiberus.mobile.android.local.RepositoriesLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { RepositoriesDatabase.getDatabase(androidContext()) }
    single { get<RepositoriesDatabase>().repositoriesDao() }
    single<RepositoriesLocalDataSource> { RepositoriesLocalDataSourceImpl(repositoriesDao = get()) }
}