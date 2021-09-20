package com.hiberus.mobile.android.domain.repository.repositories

import com.hiberus.mobile.android.model.AsyncResult
import com.hiberus.mobile.android.model.repositories.Repository
import kotlinx.coroutines.flow.Flow

interface GetRepositoriesUseCase {

    suspend operator fun invoke(
        page: Int = 1,
        forceRefresh: Boolean = false
    ): Flow<AsyncResult<List<Repository>>>
}