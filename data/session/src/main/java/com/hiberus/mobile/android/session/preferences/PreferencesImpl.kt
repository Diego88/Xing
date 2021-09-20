package com.hiberus.mobile.android.session.preferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

internal class PreferencesImpl(context: Context): Preferences {

    companion object {
        private const val SHARED_PREFS_FILE_NAME = "shared_prefs"
    }

    private val sharedPreferences: SharedPreferences by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            EncryptedSharedPreferences.create(
                context,
                SHARED_PREFS_FILE_NAME,
                getMasterKey(context),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } else {
            context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE)
        }
    }

    private fun getMasterKey(context: Context): MasterKey {
        return MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    override fun setPreference(key: String, value: Any): Boolean {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            is Boolean -> editor.putBoolean(key, value)
            else -> return false
        }
        return editor.commit()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> getPreference(key: String, defaultValue: T): T? = when (defaultValue) {
        is String -> sharedPreferences.getString(key, defaultValue as String) as T
        is Int -> sharedPreferences.getInt(key, defaultValue as Int) as T
        is Long -> sharedPreferences.getLong(key, defaultValue as Long) as T
        is Float -> sharedPreferences.getFloat(key, defaultValue as Float) as T
        is Boolean -> sharedPreferences.getBoolean(key, defaultValue as Boolean) as T
        else -> null
    }

    override fun removePreference(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key).apply()
    }

    override fun clearPreferences() {
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }
}