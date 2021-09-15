package com.hiberus.mobile.android.repository.datasource.repositories

import com.hiberus.mobile.android.model.repositories.Repository

interface RepositoriesLocalDataSource {

    suspend fun getRepositories(): List<Repository>

    suspend fun saveRepositories(repositories: List<Repository>)

    suspend fun savePage(page: Int)

    suspend fun isPageUpdated(page: Int): Boolean
}