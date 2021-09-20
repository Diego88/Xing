package com.hiberus.mobile.android.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hiberus.mobile.android.local.db.PageDb
import com.hiberus.mobile.android.local.db.RepositoryDb

@Database(entities = [RepositoryDb::class, PageDb::class], version = 1, exportSchema = false)
abstract class RepositoriesDatabase : RoomDatabase() {

    abstract fun repositoriesDao(): RepositoriesDao

    companion object {

        private const val DATABASE_NAME = "Repositories.db"

        @Volatile
        private var INSTANCE: RepositoriesDatabase? = null

        fun getDatabase(context: Context): RepositoriesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RepositoriesDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}