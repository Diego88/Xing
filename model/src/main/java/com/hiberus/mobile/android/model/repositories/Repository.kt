package com.hiberus.mobile.android.model.repositories

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    val fork: Boolean,
    val fullName: String,
    val htmlUrl: String,
    val owner: Owner,
    val private: Boolean
)