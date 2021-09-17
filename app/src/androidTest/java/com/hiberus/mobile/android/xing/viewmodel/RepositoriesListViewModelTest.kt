package com.hiberus.mobile.android.xing.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hiberus.mobile.android.commontest.CommonTestDataFactory
import com.hiberus.mobile.android.domain.repository.repositories.GetRepositoriesUseCase
import com.hiberus.mobile.android.model.AsyncResult
import com.hiberus.mobile.android.model.error.AsyncError
import com.hiberus.mobile.android.xing.common.model.ResourceState
import com.hiberus.mobile.android.xing.repositories.RepositoriesListViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import java.net.HttpURLConnection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@ExperimentalCoroutinesApi
class RepositoriesListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val getRepositoriesUseCase = mock<GetRepositoriesUseCase>()

    private val repositoriesListViewModel = RepositoriesListViewModel(getRepositoriesUseCase)

    @Test
    fun getRepositoriesReturnsSuccess() = runBlockingTest {
        val repositories = CommonTestDataFactory.makeRepositories(2)
        whenever(getRepositoriesUseCase(1)) doReturn
                 flow { AsyncResult.Success(repositories) }

        repositoriesListViewModel.fetchRepositories()
        getRepositoriesUseCase(1).collect {
            val result = repositoriesListViewModel.repositories.value
            assertTrue(result is ResourceState.Success && result.data == repositories)
        }
    }

    @Test
    fun getRepositoriesReturnsError() = runBlockingTest {
        val errorMessage = "Server Error"
        whenever(getRepositoriesUseCase(1)) doReturn
                flow {
                    AsyncResult.Error(
                        AsyncError.ServerError(
                            HttpURLConnection.HTTP_INTERNAL_ERROR,
                            errorMessage
                        ), null
                    )
                }

        repositoriesListViewModel.fetchRepositories()
        getRepositoriesUseCase(1).collect {
            val result = repositoriesListViewModel.repositories.value
            assertTrue(result is ResourceState.Error && result.error.debugMessage == errorMessage)
        }
    }

   @Test
    fun getRepositoriesReturnsLoading() = runBlockingTest {
       val repositories = CommonTestDataFactory.makeRepositories(2)
       whenever(getRepositoriesUseCase(1)) doReturn
               flow {
                   delay(5000)
                   AsyncResult.Success(repositories)
               }

       repositoriesListViewModel.fetchRepositories()
       getRepositoriesUseCase(1).collect {
           val result = repositoriesListViewModel.repositories.value
           assertTrue(result is ResourceState.Loading)
       }
    }
}