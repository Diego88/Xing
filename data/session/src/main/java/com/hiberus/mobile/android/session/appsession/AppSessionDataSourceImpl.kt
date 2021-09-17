package com.hiberus.mobile.android.session.appsession

import com.hiberus.mobile.android.repository.datasource.appsession.AppSessionDataSource

class AppSessionDataSourceImpl(
    private val sessionData: SessionData
): AppSessionDataSource {

    companion object {
        private const val KEY_LAST_OPEN_TIME = "KEY_LAST_OPEN_TIME"
        private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
    }

    override suspend fun saveLastOpenTime(timeMillis: Long): Boolean {
        sessionData.setPreferencesData(KEY_LAST_OPEN_TIME, timeMillis)
        return true
    }

    override suspend fun getLastOpenTime(): Long? =
        sessionData.getPreferencesData(KEY_LAST_OPEN_TIME, 0L)

    override suspend fun isExpiredTime(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = getLastOpenTime()

        return lastUpdateTime?.let { lastTime ->
            currentTime - lastTime > EXPIRATION_TIME
        } ?: true
    }
}