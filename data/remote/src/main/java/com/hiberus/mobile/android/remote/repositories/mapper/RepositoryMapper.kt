package com.hiberus.mobile.android.remote.repositories.mapper

import com.hiberus.mobile.android.model.repositories.Owner
import com.hiberus.mobile.android.model.repositories.Repository
import com.hiberus.mobile.android.remote.repositories.response.OwnerResponse
import com.hiberus.mobile.android.remote.repositories.response.RepositoryResponse

internal fun List<RepositoryResponse>.mapFromRemote() = this.map { it.mapFromRemote() }

private fun RepositoryResponse.mapFromRemote() =
    Repository(
        id,
        name,
        description,
        fork,
        fullName,
        htmlUrl,
        owner.mapFromRemote(),
        private
    )

private fun OwnerResponse.mapFromRemote() =
    Owner(
        id,
        avatarUrl,
        htmlUrl,
        login
    )