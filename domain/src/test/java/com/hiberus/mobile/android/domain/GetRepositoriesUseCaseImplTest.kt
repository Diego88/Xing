package com.hiberus.mobile.android.domain

import com.hiberus.mobile.android.commontest.CommonTestDataFactory
import com.hiberus.mobile.android.domain.repository.RepositoriesRepository
import com.hiberus.mobile.android.domain.repository.repositories.GetRepositoriesUseCaseImpl
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class GetRepositoriesUseCaseImplTest {

    companion object {
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_PAGE_SIZE = 30
    }

    private val repositoriesRepository = mock<RepositoriesRepository>()
    private val getRepositoriesUseCase = GetRepositoriesUseCaseImpl(repositoriesRepository)

    @Test
    fun `should call repository when call get repositories use case`() = runBlocking {
        whenever(
            repositoriesRepository.getRepositories(DEFAULT_PAGE, true)
        ) doReturn flow { CommonTestDataFactory.makeRepositories(DEFAULT_PAGE_SIZE) }

        val repositories = getRepositoriesUseCase(DEFAULT_PAGE, true)

        repositories.collect {
            verify(repositoriesRepository).getRepositories(DEFAULT_PAGE, true)
        }
    }

    @Test
    fun `should return data when call get repositories use case`() = runBlocking {
        val repositories = CommonTestDataFactory.makeRepositories(DEFAULT_PAGE_SIZE)
        whenever(
            repositoriesRepository.getRepositories(DEFAULT_PAGE, true)
        ) doReturn flow { repositories }

        val actualRepositories = getRepositoriesUseCase(DEFAULT_PAGE, true)

        actualRepositories.collect {
            assertEquals(repositories, actualRepositories)
        }
    }
}