package com.hiberus.mobile.android.local.db

import androidx.room.ColumnInfo

data class OwnerDb(
    @ColumnInfo(name = "owner_id")
    val id: Long,
    @ColumnInfo(name = "owner_avatar_url")
    val avatarUrl: String,
    @ColumnInfo(name = "owner_html_url")
    val htmlUrl: String,
    @ColumnInfo(name = "owner_login")
    val login: String
)