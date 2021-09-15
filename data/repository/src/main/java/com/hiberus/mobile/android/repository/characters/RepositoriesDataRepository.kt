package com.hiberus.mobile.android.repository.characters

import com.hiberus.mobile.android.domain.repository.RepositoriesRepository
import com.hiberus.mobile.android.model.AsyncResult
import com.hiberus.mobile.android.model.error.AsyncError
import com.hiberus.mobile.android.model.error.AsyncException
import com.hiberus.mobile.android.model.repositories.Repository
import com.hiberus.mobile.android.repository.datasource.appsession.AppSessionDataSource
import com.hiberus.mobile.android.repository.datasource.repositories.RepositoriesLocalDataSource
import com.hiberus.mobile.android.repository.datasource.repositories.RepositoriesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoriesDataRepository(
    private val remoteDataSource: RepositoriesRemoteDataSource,
    private val localDataSource: RepositoriesLocalDataSource,
    private val sessionDataSource: AppSessionDataSource
) : RepositoriesRepository {

    override suspend fun getRepositories(
        page: Int,
        forceRefresh: Boolean
    ): Flow<AsyncResult<List<Repository>>> = flow {
        if (forceRefresh
            || sessionDataSource.isExpiredTime()
            || !localDataSource.isPageUpdated(page)
        ) {
            try {
                val characters = remoteDataSource.getRepositories(page)
                localDataSource.saveRepositories(characters)
                localDataSource.savePage(page)
                sessionDataSource.saveLastOpenTime(System.currentTimeMillis())
                emit(AsyncResult.Success(characters))
            } catch (e: Exception) {
                val asyncError = (e as? AsyncException)?.asyncError
                    ?: AsyncError.UnknownError("Unknown error", e)
                emit(AsyncResult.Error(asyncError, null))
            }
        } else {
            emit(AsyncResult.Success(localDataSource.getRepositories()))
        }
    }
}