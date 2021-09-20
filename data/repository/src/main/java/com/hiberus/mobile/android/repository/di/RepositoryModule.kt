package com.hiberus.mobile.android.repository.di

import com.hiberus.mobile.android.domain.repository.RepositoriesRepository
import com.hiberus.mobile.android.repository.characters.RepositoriesDataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<RepositoriesRepository> {
        RepositoriesDataRepository(remoteDataSource = get(), localDataSource = get(), sessionDataSource = get())
    }
}