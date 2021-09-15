package com.hiberus.mobile.android.local.mapper

import com.hiberus.mobile.android.local.db.PageDb

internal fun Int.mapToCached() = PageDb(this)