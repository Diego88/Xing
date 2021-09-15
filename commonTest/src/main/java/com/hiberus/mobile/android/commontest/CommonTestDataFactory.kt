package com.hiberus.mobile.android.commontest

import com.hiberus.mobile.android.model.repositories.Owner
import com.hiberus.mobile.android.model.repositories.Repository

object CommonTestDataFactory {

    private const val EMPTY_STRING = ""
    private const val DEFAULT_ID = 0L

    fun makeRepositories(count: Int): List<Repository> {
        val repositories = mutableListOf<Repository>()
        repeat(count) {
            repositories.add(makeRepository())
        }

        return repositories
    }

    fun makeRepository(id: Long = DEFAULT_ID) = Repository(
        id,
        EMPTY_STRING,
        EMPTY_STRING,
        false,
        EMPTY_STRING,
        EMPTY_STRING,
        makeOwner(),
        false
    )

    fun makeOwner() = Owner(
        0L,
        EMPTY_STRING,
        EMPTY_STRING,
        EMPTY_STRING
    )
}