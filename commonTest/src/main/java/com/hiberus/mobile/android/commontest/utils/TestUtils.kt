package com.hiberus.mobile.android.commontest.utils

fun readResourceFile(path: String) =
    object {}.javaClass.classLoader?.getResource(path)?.readText() ?: ""