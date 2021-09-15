package com.hiberus.mobile.android.local.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Repositories")
data class RepositoryDb(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val fork: Boolean,
    val fullName: String,
    val htmlUrl: String,
    @ColumnInfo(name = "private")
    val isPrivate: Boolean,
    @Embedded
    val owner: OwnerDb
)