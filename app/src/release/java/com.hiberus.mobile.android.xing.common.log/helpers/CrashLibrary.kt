package com.hiberus.mobile.android.xing.common.log.helpers

interface CrashLibrary {

    fun log(priority: Int, tag: String?, message: String)

    fun logException(throwable: Throwable)

    fun setUser(userId: String)
}
