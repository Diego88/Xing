package com.hiberus.mobile.android.session.appsession

import com.hiberus.mobile.android.session.preferences.Preferences
import java.util.TreeMap

class SessionDataImpl(
    private val preferences: Preferences
) : SessionData {

    private val session = TreeMap<String, Any?>()

    override fun setPreferencesData(key: String, value: Any): Boolean {
        return preferences.setPreference(key, value)
    }

    override fun <T> getPreferencesData(key: String, defaultValue: T): T? =
        preferences.getPreference(key, defaultValue)

    override fun setSessionData(key: String, value: Any?) {
        session[key] = value
    }

    override fun getSessionData(key: String, value: Any?) = session[key]
}