package com.hiberus.mobile.android.model.repositories

data class Repository(
    val id: Long,
    val name: String,
    val description: String,
    val fork: Boolean,
    val htmlUrl: String,
    val owner: Owner,
    val private: Boolean
)