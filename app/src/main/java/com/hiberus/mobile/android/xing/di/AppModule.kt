package com.hiberus.mobile.android.xing.di

import com.hiberus.mobile.android.xing.repositories.RepositoriesListAdapter
import com.hiberus.mobile.android.xing.repositories.RepositoriesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { RepositoriesListAdapter() }
    viewModel { RepositoriesListViewModel(getRepositoryUseCase = get()) }
}