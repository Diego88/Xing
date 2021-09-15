package com.hiberus.mobile.android.local

import com.hiberus.mobile.android.commontest.CommonTestDataFactory
import com.hiberus.mobile.android.local.factory.LocalTestDataFactory
import com.hiberus.mobile.android.model.repositories.Repository
import com.nhaarman.mockitokotlin2.*
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
    fun `should map and return all repositories`() = runBlockingTest {
        val repositories = LocalTestDataFactory.makeRepositoriesDb(2)
        whenever(repositoriesDao.getRepositories()) doReturn repositories

        val actualRepositories = repositoriesLocalDataSourceImpl.getRepositories()

        verify(repositoriesDao).getRepositories()
        assertEquals(CommonTestDataFactory.makeRepositories(2), actualRepositories)
    }

    @Test
    fun `should map and return requested repositories`() = runBlockingTest {
        val repositoryId = 1L
        val repository = LocalTestDataFactory.makeRepositoryDb(repositoryId)
        whenever(repositoriesDao.getRepositories()) doReturn listOf(repository)

        val expectedRepository = CommonTestDataFactory.makeRepository(repositoryId)
        val actualRepository = repositoriesLocalDataSourceImpl.getRepositories()

        verify(repositoriesDao).getRepositories()
        assertEquals(listOf(expectedRepository), actualRepository)
    }

    @Test
    fun `should return empty list if does database is empty`() = runBlockingTest {
        whenever(repositoriesDao.getRepositories()) doReturn emptyList()

        val actualRepository = repositoriesLocalDataSourceImpl.getRepositories()

        verify(repositoriesDao).getRepositories()
        assertEquals(emptyList<Repository>(), actualRepository)
    }

    @Test
    fun `should map repositories to store on database`() = runBlockingTest {
        val requestedRepositoryId = 2L
        val repository = CommonTestDataFactory.makeRepository(requestedRepositoryId)
        val repositoryDb = LocalTestDataFactory.makeRepositoryDb(requestedRepositoryId)

        repositoriesLocalDataSourceImpl.saveRepositories(listOf(repository))

        verify(repositoriesDao).insertRepositories(listOf(repositoryDb))
    }
}