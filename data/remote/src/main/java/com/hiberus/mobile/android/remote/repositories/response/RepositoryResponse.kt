package com.hiberus.mobile.android.remote.repositories.response

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    val id: Long,
    val name: String,
    val description: String,
    val fork: Boolean,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val ownerResponse: OwnerResponse,
    val private: Boolean
)