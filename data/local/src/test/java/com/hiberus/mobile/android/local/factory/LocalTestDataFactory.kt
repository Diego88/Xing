package com.hiberus.mobile.android.local.factory

import com.hiberus.mobile.android.local.db.OwnerDb
import com.hiberus.mobile.android.local.db.RepositoryDb

object LocalTestDataFactory {

    const val DEFAULT_OFFSET = 0
    const val DEFAULT_LIMIT = 5
    private const val EMPTY_STRING = ""
    private const val DEFAULT_ID = 0L

    internal fun makeRepositoriesDb(count: Int, id: Long = DEFAULT_ID): List<RepositoryDb> {
        val repositories = mutableListOf<RepositoryDb>()
        repeat(count) {
            repositories.add(makeRepositoryDb(id))
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