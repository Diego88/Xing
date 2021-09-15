package com.hiberus.mobile.android.local.factory

import com.hiberus.mobile.android.local.db.OwnerDb
import com.hiberus.mobile.android.local.db.RepositoryDb

object LocalTestDataFactory {

    private const val EMPTY_STRING = ""
    private const val DEFAULT_ID = 0L

    internal fun makeRepositoriesDb(count: Int): List<RepositoryDb> {
        val repositories = mutableListOf<RepositoryDb>()
        repeat(count) {
            repositories.add(makeRepositoryDb())
        }

        return repositories
    }

    fun makeRepositoryDb(id: Long = DEFAULT_ID) = RepositoryDb(
        id,
        EMPTY_STRING,
        EMPTY_STRING,
        false,
        EMPTY_STRING,
        EMPTY_STRING,
        false,
        makeOwnerDb()
    )

    private fun makeOwnerDb() = OwnerDb(
        0L,
        EMPTY_STRING,
        EMPTY_STRING,
        EMPTY_STRING
    )
}