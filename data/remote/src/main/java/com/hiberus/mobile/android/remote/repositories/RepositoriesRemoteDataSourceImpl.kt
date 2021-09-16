package com.hiberus.mobile.android.remote.repositories

import com.hiberus.mobile.android.model.repositories.Repository
import com.hiberus.mobile.android.remote.repositories.error.RemoteErrorManager
import com.hiberus.mobile.android.remote.repositories.mapper.mapFromRemote
import com.hiberus.mobile.android.repository.datasource.repositories.RepositoriesRemoteDataSource

class RepositoriesRemoteDataSourceImpl(
    private val repositoriesService: RepositoriesService
) : RepositoriesRemoteDataSource {

    override suspend fun getRepositories(page: Int, pageSize: Int): List<Repository> {
        return RemoteErrorManager.wrap {
            repositoriesService.getRespositories(page, pageSize).mapFromRemote()
        }
    }
}