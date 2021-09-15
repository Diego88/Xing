package com.hiberus.mobile.android.remote.factory

import com.hiberus.mobile.android.remote.repositories.response.OwnerResponse
import com.hiberus.mobile.android.remote.repositories.response.RepositoryResponse

object RemoteTestDataFactory {

    private const val EMPTY_STRING = ""

    internal fun makeRepositoriesResponse(count: Int): List<RepositoryResponse> {
        val repositories = mutableListOf<RepositoryResponse>()
        repeat(count) {
            repositories.add(makeRepositoryResponse())
        }

        return repositories
    }

    private fun makeRepositoryResponse() = RepositoryResponse(
        0L,
        EMPTY_STRING,
        EMPTY_STRING,
        false,
        EMPTY_STRING,
        EMPTY_STRING,
        makeOwnerResponse(),
        false
    )

    private fun makeOwnerResponse() = OwnerResponse(
        0L,
        EMPTY_STRING,
        EMPTY_STRING,
        EMPTY_STRING
    )
}