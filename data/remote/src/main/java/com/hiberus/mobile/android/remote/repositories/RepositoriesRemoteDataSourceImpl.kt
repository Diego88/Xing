package com.hiberus.mobile.android.remote.repositories

import com.hiberus.mobile.android.data.datasource.repositories.RepositoriesRemoteDataSource
import com.hiberus.mobile.android.model.repositories.Repository
import com.hiberus.mobile.android.remote.repositories.error.RemoteErrorManager
import com.hiberus.mobile.android.remote.repositories.mapper.mapFromRemote

class RepositoriesRemoteDataSourceImpl(
    private val repositoriesService: RepositoriesService
) : RepositoriesRemoteDataSource {

    override suspend fun getRepositories(page: Int, pageSize: Int): List<Repository> {
        return RemoteErrorManager.wrap {
            repositoriesService.getRespositories(page, pageSize).mapFromRemote()
        }
    }
}