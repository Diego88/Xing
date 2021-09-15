package com.hiberus.mobile.android.local

import android.app.Application
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
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
@Config(manifest= Config.NONE, sdk = [Build.VERSION_CODES.R])
class RepositoriesDaoTest {

    private lateinit var database: RepositoriesDatabase
    private lateinit var repositoriesDao: RepositoriesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        database = Room.inMemoryDatabaseBuilder(context, RepositoriesDatabase::class.java).build()
        repositoriesDao = database.repositoriesDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `should save and return all repositories`() = runBlocking {
        val repositories = LocalTestDataFactory.makeRepositoriesDb(2)

        repositoriesDao.insertRepositories(repositories)
        val actualRepositories = repositoriesDao.getRepositories()

        assertEquals(actualRepositories, repositories)
    }

    @Test
    fun `should save and return requested repositories`() = runBlocking {
        val repository = LocalTestDataFactory.makeRepositoryDb(1L)
        val otherRepository = LocalTestDataFactory.makeRepositoryDb(2L)

        repositoriesDao.insertRepositories(
            listOf(
                repository,
                otherRepository
            )
        )
        val actualRepository = repositoriesDao.getRepositories()?.first()

        assertEquals(repository, actualRepository)
    }

    @Test
    fun `should not return requested repositories if it does not exist`() = runBlocking {
        val requestedRepositoryId = 4L
        val otherRepository = LocalTestDataFactory.makeRepositoryDb(3L)

        repositoriesDao.insertRepositories(listOf(otherRepository))
        val actualRepository = repositoriesDao.getRepositories()?.filter { it.id == requestedRepositoryId }

        assertEquals(null, actualRepository)
    }
}