package com.hiberus.mobile.android.xing.util

import com.hiberus.mobile.android.model.repositories.Repository

interface OnRepositoryLongClickListener {
    fun onLongClicked(repository: Repository)
}