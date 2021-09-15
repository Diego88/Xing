package com.hiberus.mobile.android.remote.utils

fun readResourceFile(path: String) =
    object {}.javaClass.classLoader?.getResource(path)?.readText() ?: ""