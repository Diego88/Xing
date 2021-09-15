package com.hiberus.mobile.android.remote.repositories.response

import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val login: String
)