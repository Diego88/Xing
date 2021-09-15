package com.hiberus.mobile.android.remote.repositories

import com.hiberus.mobile.android.remote.repositories.response.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesService {

    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    @GET("/orgs/xing/repos")
    suspend fun getRespositories(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): List<RepositoryResponse>
}