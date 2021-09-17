package com.hiberus.mobile.android.xing.common.log

import com.hiberus.mobile.android.xing.common.log.helpers.CrashLibraryTree
import com.hiberus.mobile.android.xing.TimberLog
import timber.log.Timber

object TimberLogImpl : TimberLog {

    override fun init() {
        init("")
    }

    override fun init(userId: String) {
        Timber.plant(CrashLibraryTree(userId))
    }
}