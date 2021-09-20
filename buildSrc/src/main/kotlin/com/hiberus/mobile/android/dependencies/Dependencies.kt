package com.hiberus.mobile.android.dependencies

class Dependencies {

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val junitExt = "androidx.test.ext:${Versions.Test.junit}"
        const val mockk = "io.mockk:mockk:${Versions.Test.mockk}"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.Test.mockitoKotlin}"
        const val mockitoCore = "org.mockito:mockito-core:${Versions.Test.mockitoKotlin}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.Test.mockitoInline}"
        const val mockitoAndroid = "org.mockito:mockito-android:${Versions.Test.mockitoAndroid}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.Test.mockWebServer}"
        const val robolectric = "org.robolectric:robolectric:${Versions.Test.robolectric}"
        const val koinTest = "io.insert-koin:koin-test:${Versions.Koin.koin}"
        const val koinTestJunit = "io.insert-koin:koin-test-junit4:${Versions.Koin.koin}"
        const val koinCore = "io.insert-koin:koin-test-core:${Versions.Koin.koin}"
    }

    object AndroidTest {
        const val core = "androidx.test:core:${Versions.AndroidTest.core}"
        const val testCoreKtx = "androidx.test:core-ktx:${Versions.AndroidTest.testCoreKtx}"
        const val androidJunit = "androidx.test.ext:junit:${Versions.AndroidTest.androidJunit}"
        const val androidJunitKtx = "androidx.test.ext:junit-ktx:${Versions.AndroidTest.androidJunit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.AndroidTest.espressoCore}"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.AndroidTest.espressoCore}"
        const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.AndroidTest.espressoCore}"
        const val roomTest = "androidx.room:room-testing:${Versions.AndroidX.room}"
        const val runner = "androidx.test:runner:${Versions.AndroidTest.runner}"
        const val rules = "androidx.test:rules:${Versions.AndroidTest.rules}"
        const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.AndroidTest.fragmentTesting}"
        const val archCoreTest = "androidx.arch.core:core-testing:${Versions.AndroidTest.coreTesting}"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val material = "com.google.android.material:material:${Versions.AndroidX.material}"
        const val room = "androidx.room:room-runtime:${Versions.AndroidX.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.AndroidX.room}"
        const val roomCoroutines = "androidx.room:room-coroutines:${Versions.AndroidX.room}"
        const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycle}"
        const val support = "androidx.legacy:legacy-support-v4:${Versions.support}"
        const val crypto = "androidx.security:security-crypto:${Versions.crypto}"
    }

    object AndroidTools {
        const val desugaring = "com.android.tools:desugar_jdk_libs:${Versions.desugaring}"
    }

    object Koin {
        const val koinCore = "io.insert-koin:koin-core:${Versions.Koin.koin}"
        const val koin = "io.insert-koin:koin-android:${Versions.Koin.koin}"
        const val koinCompiler = "io.insert-koin:koin-androidx-scope:${Versions.Koin.koin}"
        const val koinViewModel = "io.insert-koin:koin-androidx-viewmodel:${Versions.Koin.koin}"
    }

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlin}"
        const val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Kotlin.kotlin}"
        const val ktxCore = "androidx.core:core-ktx:${Versions.Kotlin.ktx}"
        const val coroutineAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
    }

    object Firebase {
        const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics:${Versions.Firebase.firebaseCrashlytics}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    }

    object OkHttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
//        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
//        const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
//        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object DeveloperTools {
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.DeveloperTools.leakCanary}"
        const val timber = "com.jakewharton.timber:timber:${Versions.DeveloperTools.timber}"
        const val stetho = "com.facebook.stetho:stetho-okhttp3:${Versions.DeveloperTools.stetho}"
    }
}