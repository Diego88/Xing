package com.hiberus.mobile.android.local

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.hiberus.mobile.android.local.factory.LocalTestDataFactory
import com.hiberus.mobile.android.local.factory.LocalTestDataFactory.DEFAULT_LIMIT
import com.hiberus.mobile.android.local.factory.LocalTestDataFactory.DEFAULT_OFFSET
import com.hiberus.mobile.android.model.repositories.Repository
import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE, sdk = [Build.VERSION_CODES.Q])
class RepositoriesDaoTest {

    private lateinit var database: RepositoriesDatabase
    private lateinit var repositoriesDao: RepositoriesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, RepositoriesDatabase::class.java).build()
        repositoriesDao = database.repositoriesDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun `should save and return all repositories`() = runBlocking {
        val repositories = LocalTestDataFactory.makeRepositoriesDb(1)

        repositoriesDao.insertRepositories(repositories)
        val actualRepositories = repositoriesDao.getRepositories(DEFAULT_OFFSET, DEFAULT_LIMIT)

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
        val actualRepository = repositoriesDao.getRepositories(DEFAULT_OFFSET, DEFAULT_LIMIT)?.first()

        assertEquals(repository, actualRepository)
    }

    @Test
    fun `should not return requested repositories if it does not exist`() = runBlocking {
        val requestedRepositoryId = 4L
        val otherRepository = LocalTestDataFactory.makeRepositoriesDb(1, 3L)

        repositoriesDao.insertRepositories(otherRepository)
        val actualRepository = repositoriesDao.getRepositories(DEFAULT_OFFSET, DEFAULT_LIMIT)?.filter {
            it.id == requestedRepositoryId
        }

        assertEquals(listOf<Repository>(), actualRepository)
    }
}