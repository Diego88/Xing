package com.hiberus.mobile.android.session

import com.hiberus.mobile.android.session.preferences.Preferences
import java.util.TreeMap

class SessionData(
    private val preferences: Preferences
) {

    private val session = TreeMap<String, Any?>()

    internal fun setPreferencesData(key: String, value: Any): Boolean {
        return preferences.setPreference(key, value)
    }

    internal fun <T> getPreferencesData(key: String, defaultValue: T): T? =
        preferences.getPreference(key, defaultValue)

    internal fun setSessionData(key: String, value: Any?) {
        session[key] = value
    }

    internal fun getSessionData(key: String, value: Any?) = session[key]
}