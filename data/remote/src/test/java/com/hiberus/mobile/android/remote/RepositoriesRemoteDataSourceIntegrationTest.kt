package com.hiberus.mobile.android.remote

import com.hiberus.mobile.android.commontest.utils.readResourceFile
import com.hiberus.mobile.android.model.error.AsyncError
import com.hiberus.mobile.android.model.error.AsyncException
import com.hiberus.mobile.android.remote.factory.RemoteTestDataFactory.DEFAULT_PAGE
import com.hiberus.mobile.android.remote.factory.RemoteTestDataFactory.DEFAULT_PAGE_SIZE
import com.hiberus.mobile.android.remote.repositories.di.remoteModule
import com.hiberus.mobile.android.repository.datasource.repositories.RepositoriesRemoteDataSource
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

/**
 * Open address [http://localhost:8080/org/xing/repos] in a browser while tests are executing
 */
class RepositoriesRemoteDataSourceIntegrationTest: KoinTest {

    companion object {
        private const val SERVER_URL = "/org/xing/repos"
    }

    private val repositoriesRemoteDataSource: RepositoriesRemoteDataSource
        by inject(RepositoriesRemoteDataSource::class.java)
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        mockWebServer.url(SERVER_URL)
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
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(readResourceFile("repositories.json"))
        )

        val request = mockWebServer.takeRequest()
        assertEquals(SERVER_URL, request.path)

//        val actualRepos = repositoriesRemoteDataSource.getRepositories(DEFAULT_PAGE, DEFAULT_PAGE_SIZE)
//        assertEquals(request.body, actualRepos)
    }

    @Test
    fun `should throw AsyncException when request has not been successful`() = runBlocking<Unit> {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .setBody(readResourceFile("repositories.json"))
        )

        try {
            repositoriesRemoteDataSource.getRepositories(DEFAULT_PAGE, DEFAULT_PAGE_SIZE)
        } catch (e: AsyncException) {
            val asyncError = AsyncError.ServerError(
                code = 400,
                debugMessage = mockWebServer.url("").toString() + ""
            )
            assertEquals(asyncError, e.asyncError)
        }
    }
}