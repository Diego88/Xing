package com.hiberus.mobile.android.domain.repository

import com.hiberus.mobile.android.model.AsyncResult
import com.hiberus.mobile.android.model.repositories.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoriesRepository {

    suspend fun getRepositories(
        page: Int,
        forceRefresh: Boolean
    ): Flow<AsyncResult<List<Repository>>>
}