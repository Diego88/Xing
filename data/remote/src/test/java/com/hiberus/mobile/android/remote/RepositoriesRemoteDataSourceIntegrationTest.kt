package com.hiberus.mobile.android.remote

import com.hiberus.mobile.android.data.datasource.repositories.RepositoriesRemoteDataSource
import com.hiberus.mobile.android.model.error.AsyncError
import com.hiberus.mobile.android.model.error.AsyncException
import com.hiberus.mobile.android.remote.repositories.di.remoteModule
import com.hiberus.mobile.android.remote.utils.readResourceFile
import java.net.HttpURLConnection
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest

class RepositoriesRemoteDataSourceIntegrationTest: KoinTest {

    private val repositoriesRemoteDataSource: RepositoriesRemoteDataSource
        by inject(RepositoriesRemoteDataSource::class.java)
    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start()
        startKoin {
            modules(remoteModule)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        stopKoin()
    }

    @Test
    fun `should return response when request has been successful`() = runBlocking {
        mockWebServer.apply {
            enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readResourceFile("repositories.json"))
            )
        }

        val actualRepos = repositoriesRemoteDataSource.getRepositories(1, 5)
        val request = mockWebServer.takeRequest()

        assertEquals("/org/xing/repos", request.path)
        assertEquals(request.body, actualRepos)
    }

    @Test
    fun `should throw AsyncException when request has not been successful`() = runBlocking<Unit> {
        mockWebServer.apply {
            enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                    .setBody(readResourceFile("repositories.json")))
        }

        try {
            repositoriesRemoteDataSource.getRepositories(1, 5)
        } catch (e: AsyncException) {
            val asyncError = AsyncError.ServerError(
                code = 400,
                debugMessage = mockWebServer.url("").toString() + ""
            )
            assertEquals(asyncError, e.asyncError)
        }
    }
}