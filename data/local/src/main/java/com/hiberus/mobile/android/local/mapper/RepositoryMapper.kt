package com.hiberus.mobile.android.local.mapper

import com.hiberus.mobile.android.local.db.OwnerDb
import com.hiberus.mobile.android.local.db.RepositoryDb
import com.hiberus.mobile.android.model.repositories.Owner
import com.hiberus.mobile.android.model.repositories.Repository

internal fun List<RepositoryDb>.mapFromCached() = this.map { it.mapFromCached() }

private fun RepositoryDb.mapFromCached() =
    Repository(
        id,
        name,
        description,
        fork,
        fullName,
        htmlUrl,
        owner.mapFromCached(),
        isPrivate
    )

private fun OwnerDb.mapFromCached() =
    Owner(
        id,
        avatarUrl,
        htmlUrl,
        login
    )

internal fun List<Repository>.mapToCached(): List<RepositoryDb> = this.map { it.mapToCached() }

private fun Repository.mapToCached() =
    RepositoryDb(
        id,
        name,
        description,
        fork,
        fullName,
        htmlUrl,
        private,
        owner.mapToCached()
    )

private fun Owner.mapToCached() =
    OwnerDb(
        id,
        avatarUrl,
        htmlUrl,
        login
    )