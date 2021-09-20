package com.hiberus.mobile.android.domain.repository.repositories

import com.hiberus.mobile.android.domain.repository.RepositoriesRepository
import com.hiberus.mobile.android.model.AsyncResult
import com.hiberus.mobile.android.model.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetRepositoriesUseCaseImpl(
    private val repositoriesRepository: RepositoriesRepository
) : GetRepositoriesUseCase {

    override suspend operator fun invoke(
        page: Int,
        forceRefresh: Boolean
    ): Flow<AsyncResult<List<Repository>>> =
        withContext(Dispatchers.IO) {
            repositoriesRepository.getRepositories(page, forceRefresh)
        }
}