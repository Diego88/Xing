package com.hiberus.mobile.android.local

import com.hiberus.mobile.android.commontest.CommonTestDataFactory
import com.hiberus.mobile.android.local.factory.LocalTestDataFactory
import com.hiberus.mobile.android.local.factory.LocalTestDataFactory.DEFAULT_LIMIT
import com.hiberus.mobile.android.local.factory.LocalTestDataFactory.DEFAULT_OFFSET
import com.hiberus.mobile.android.model.repositories.Repository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoriesLocalDataSourceImplTest {

    private val repositoriesDao = mock<RepositoriesDao>()
    private val repositoriesLocalDataSourceImpl = RepositoriesLocalDataSourceImpl(repositoriesDao)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repositoriesDao)
    }

    @Test
    fun `should return all repositories`() = runBlockingTest {
        val repositories = LocalTestDataFactory.makeRepositoriesDb(2)
        whenever(repositoriesDao.getRepositories(DEFAULT_OFFSET, DEFAULT_LIMIT)) doReturn repositories

        val actualRepositories = repositoriesLocalDataSourceImpl.getRepositories(0, 5)

        verify(repositoriesDao).getRepositories(DEFAULT_OFFSET, DEFAULT_LIMIT)
        assertEquals(CommonTestDataFactory.makeRepositories(2), actualRepositories)
    }

    @Test
    fun `should return requested repositories`() = runBlockingTest {
        val repositoryId = 1L
        val repository = LocalTestDataFactory.makeRepositoryDb(repositoryId)
        whenever(repositoriesDao.getRepositories(DEFAULT_OFFSET, DEFAULT_LIMIT)) doReturn listOf(repository)

        val expectedRepository = CommonTestDataFactory.makeRepository(repositoryId)
        val actualRepository = repositoriesLocalDataSourceImpl.getRepositories(0, 5)

        verify(repositoriesDao).getRepositories(DEFAULT_OFFSET, DEFAULT_LIMIT)
        assertEquals(listOf(expectedRepository), actualRepository)
    }

    @Test
    fun `should return empty list if database is empty`() = runBlockingTest {
        whenever(repositoriesDao.getRepositories(DEFAULT_OFFSET, DEFAULT_LIMIT)) doReturn emptyList()

        val actualRepository =
            repositoriesLocalDataSourceImpl.getRepositories(DEFAULT_OFFSET, DEFAULT_LIMIT)

        verify(repositoriesDao).getRepositories(DEFAULT_OFFSET, DEFAULT_LIMIT)
        assertEquals(emptyList<Repository>(), actualRepository)
    }

    @Test
    fun `should save repositories on database`() = runBlockingTest {
        val requestedRepositoryId = 2L
        val repository = CommonTestDataFactory.makeRepository(requestedRepositoryId)
        val repositoryDb = LocalTestDataFactory.makeRepositoryDb(requestedRepositoryId)

        repositoriesLocalDataSourceImpl.saveRepositories(listOf(repository))

        verify(repositoriesDao).insertRepositories(listOf(repositoryDb))
    }
}