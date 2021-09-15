package com.hiberus.mobile.android.remote.repositories

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoriesServiceFactory {

    fun makeRepositoriesService(isDebug: Boolean): RepositoriesService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug),
            makeStethoInterceptor(isDebug)
        )
        val retrofit = Retrofit.Builder()
            .baseUrl(RepositoriesService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RepositoriesService::class.java)
    }

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        stethoInterceptor: StethoInterceptor?
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)

        stethoInterceptor?.let { okHttpClient.addNetworkInterceptor(it) }

        return okHttpClient.build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return logging
    }

    private fun makeStethoInterceptor(isDebug: Boolean): StethoInterceptor? {
        return if (isDebug) {
            StethoInterceptor()
        } else {
            null
        }
    }
}