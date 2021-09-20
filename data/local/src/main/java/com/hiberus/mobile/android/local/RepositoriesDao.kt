package com.hiberus.mobile.android.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hiberus.mobile.android.local.db.PageDb
import com.hiberus.mobile.android.local.db.RepositoryDb

@Dao
interface RepositoriesDao {

    @Query("SELECT * FROM Repositories LIMIT :limit OFFSET :offset")
    suspend fun getRepositories(offset: Int, limit: Int): List<RepositoryDb>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<RepositoryDb>)

    @Query("SELECT page FROM Page")
    suspend fun getPage(): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPage(page: PageDb)
}