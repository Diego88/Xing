package com.hiberus.mobile.android.local.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Page")
class PageDb(
    @PrimaryKey
    val id: Int,
    val page: Int
) {
    @Ignore
    constructor(page: Int): this(0, page)
}