package com.hiberus.mobile.android.session

import com.hiberus.mobile.android.commontest.CommonTestDataFactory
import com.hiberus.mobile.android.repository.characters.RepositoriesDataRepository
import com.hiberus.mobile.android.repository.datasource.appsession.AppSessionDataSource
import com.hiberus.mobile.android.repository.datasource.repositories.RepositoriesLocalDataSource
import com.hiberus.mobile.android.repository.datasource.repositories.RepositoriesRemoteDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class RepositoriesDataRepositoryTest {

    companion object {
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_PAGE_SIZE = 30
    }

    private val repositoriesRemoteDataSource = mock<RepositoriesRemoteDataSource>()
    private val repositoriesLocalDataSource = mock<RepositoriesLocalDataSource>()
    private val appSessionDataSource = mock<AppSessionDataSource>()

    private val repositoriesDataRepository = RepositoriesDataRepository(
        repositoriesRemoteDataSource,
        repositoriesLocalDataSource,
        appSessionDataSource
    )

    @Test
    fun `should return all remote repositories when force refresh`() = runBlockingTest {
        whenever(
            repositoriesRemoteDataSource.getRepositories(DEFAULT_PAGE, DEFAULT_PAGE_SIZE)
        ) doReturn CommonTestDataFactory.makeRepositories(DEFAULT_PAGE_SIZE)

        val actualRepositories =
            repositoriesDataRepository.getRepositories(DEFAULT_PAGE, true)

        actualRepositories.collect {
            verify(repositoriesRemoteDataSource).getRepositories(any(), any())
        }
    }

    @Test
    fun `should save all repositories when force refresh`() = runBlockingTest{
        val repositories = CommonTestDataFactory.makeRepositories(DEFAULT_PAGE_SIZE)
        whenever(repositoriesRemoteDataSource.getRepositories(DEFAULT_PAGE, DEFAULT_PAGE_SIZE)) doReturn
                repositories

        val actualRepositories =
            repositoriesDataRepository.getRepositories(DEFAULT_PAGE, true)

        actualRepositories.collect {
            verify(repositoriesLocalDataSource).saveRepositories(repositories)
        }
    }
}