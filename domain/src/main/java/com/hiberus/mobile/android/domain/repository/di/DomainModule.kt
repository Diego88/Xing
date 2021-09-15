package com.hiberus.mobile.android.domain.repository.di

import com.hiberus.mobile.android.domain.repository.repositories.GetRepositoriesUseCase
import com.hiberus.mobile.android.domain.repository.repositories.GetRepositoriesUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<GetRepositoriesUseCase> { GetRepositoriesUseCaseImpl(repositoriesRepository = get()) }
}
