package com.hiberus.mobile.android.dependencies

object Classpaths {
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlin}"
    const val firebaseCrashlyticsGradlePlugin =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.Firebase.firebaseCrashlyticsGradlePlugin}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.AndroidX.navigation}"
}