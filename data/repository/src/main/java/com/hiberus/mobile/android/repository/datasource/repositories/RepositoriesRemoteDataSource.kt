package com.hiberus.mobile.android.repository.datasource.repositories

import com.hiberus.mobile.android.model.repositories.Repository

interface RepositoriesRemoteDataSource {

    suspend fun getRepositories(page: Int): List<Repository>
}