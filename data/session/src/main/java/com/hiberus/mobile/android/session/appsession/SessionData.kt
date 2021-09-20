package com.hiberus.mobile.android.session.appsession

interface SessionData {

    fun setPreferencesData(key: String, value: Any): Boolean

    fun <T> getPreferencesData(key: String, defaultValue: T): T?

    fun setSessionData(key: String, value: Any?)

    fun getSessionData(key: String, value: Any?): Any?
}