package com.hiberus.mobile.android.local

import android.app.Application
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.hiberus.mobile.android.commontest.CommonTestDataFactory
import com.hiberus.mobile.android.local.factory.LocalTestDataFactory
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE, sdk=[Build.VERSION_CODES.R])
class RepositoriesLocalDataSourceIntegrationTest {
    private lateinit var database: RepositoriesDatabase
    private lateinit var repositoriesDao: RepositoriesDao
    private lateinit var repositoriesLocalDataSource: RepositoriesLocalDataSourceImpl

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        database = Room.inMemoryDatabaseBuilder(context, RepositoriesDatabase::class.java).build()
        repositoriesDao = database.repositoriesDao()
        repositoriesLocalDataSource = RepositoriesLocalDataSourceImpl(repositoriesDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `should save and return all repositories`() = runBlocking<Unit> {
        val repository = LocalTestDataFactory.makeRepositoryDb()

        repositoriesDao.insertRepositories(listOf(repository))
        val actualRepositories = repositoriesLocalDataSource.getRepositories()

        assertEquals(CommonTestDataFactory.makeRepository(), actualRepositories)
    }

    @Test
    fun `should save and return requested repositories`() = runBlocking {
        val requestedRepositoryId = 1L
        val repository = LocalTestDataFactory.makeRepositoryDb(requestedRepositoryId)
        val otherRepository = LocalTestDataFactory.makeRepositoryDb(2L)

        repositoriesDao.insertRepositories(listOf(repository))
        repositoriesDao.insertRepositories(listOf(otherRepository))
        val actualRepository = repositoriesLocalDataSource.getRepositories().filter {
            it.id == requestedRepositoryId
        }

        assertEquals(CommonTestDataFactory.makeRepository(requestedRepositoryId), actualRepository)
    }

    @Test
    fun `should not return requested repositories if it does not exist`() = runBlocking {
        val requestedRepositoryId = 1L
        val otherRepository = LocalTestDataFactory.makeRepositoryDb(2L)

        repositoriesDao.insertRepositories(listOf(otherRepository))
        val actualRepository = repositoriesLocalDataSource.getRepositories().filter {
            it.id == requestedRepositoryId
        }

        assertEquals(null, actualRepository)
    }
}