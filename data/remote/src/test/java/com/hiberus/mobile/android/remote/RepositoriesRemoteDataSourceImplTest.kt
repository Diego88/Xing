package com.hiberus.mobile.android.remote

import com.hiberus.mobile.android.commontest.CommonTestDataFactory
import com.hiberus.mobile.android.model.error.AsyncError
import com.hiberus.mobile.android.model.error.AsyncException
import com.hiberus.mobile.android.remote.factory.RemoteTestDataFactory
import com.hiberus.mobile.android.remote.repositories.RepositoriesRemoteDataSourceImpl
import com.hiberus.mobile.android.remote.repositories.RepositoriesService
import com.hiberus.mobile.android.repository.datasource.repositories.RepositoriesRemoteDataSource
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import java.net.HttpURLConnection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class RepositoriesRemoteDataSourceImplTest {

    private val repositoriesService: RepositoriesService = mock()

    private lateinit var repositoriesRemoteDataSource: RepositoriesRemoteDataSource

    @Before
    fun setUp() {
        repositoriesRemoteDataSource = RepositoriesRemoteDataSourceImpl(repositoriesService)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repositoriesService)
    }

    @Test
    fun `should return response when request has been successful`() = runBlockingTest {
        whenever(repositoriesService.getRespositories(1, 5)) doReturn
                RemoteTestDataFactory.makeRepositoriesResponse(5)

        val actualRepos = repositoriesRemoteDataSource.getRepositories(1, 5)
        val expectedRepos = CommonTestDataFactory.makeRepositories(5)

        verify(repositoriesService).getRespositories(1, 5)
        assertEquals(expectedRepos, actualRepos)
    }

    @Test
    fun `should throw AsyncException when request has not been successful`() = runBlockingTest {
        whenever(repositoriesService.getRespositories(1, 5)) doThrow HttpException(
            Response.error<Unit>(HttpURLConnection.HTTP_INTERNAL_ERROR, "".toResponseBody(null))
        )

        try {
            repositoriesRemoteDataSource.getRepositories(1, 5)
        } catch (e: AsyncException) {
            verify(repositoriesService).getRespositories(1, 5)
            val asyncError = AsyncError.ServerError(
                code = 500,
                debugMessage = "http://localhost/"
            )
            assertEquals(asyncError, e.asyncError)
        }
    }
}