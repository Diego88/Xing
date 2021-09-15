package com.hiberus.mobile.android.session.preferences

interface Preferences {

    fun setPreference(key: String, value: Any): Boolean

    fun <T> getPreference(key: String, defaultValue: T): T?

    fun removePreference(key: String)

    fun clearPreferences()
}