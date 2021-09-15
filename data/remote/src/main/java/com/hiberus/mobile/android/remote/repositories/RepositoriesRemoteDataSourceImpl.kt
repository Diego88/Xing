package com.hiberus.mobile.android.remote.repositories

import com.hiberus.mobile.android.model.repositories.Repository
import com.hiberus.mobile.android.remote.repositories.error.RemoteErrorManager
import com.hiberus.mobile.android.remote.repositories.mapper.mapFromRemote
import com.hiberus.mobile.android.repository.datasource.repositories.RepositoriesRemoteDataSource

class RepositoriesRemoteDataSourceImpl(
    private val repositoriesService: RepositoriesService
) : RepositoriesRemoteDataSource {

    companion object {
        private const val PAGE_SIZE = 100
    }

    override suspend fun getRepositories(page: Int): List<Repository> {
        return RemoteErrorManager.wrap {
            repositoriesService.getRespositories(page, PAGE_SIZE).mapFromRemote()
        }
    }
}