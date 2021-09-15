package com.hiberus.mobile.android.model

import com.hiberus.mobile.android.model.error.AsyncError

sealed class AsyncResult<out T>(open val data: T?) {
    data class Success<out T>(override val data: T?) : AsyncResult<T>(data)
    data class Error<out T>(val error: AsyncError, override val data: T?) : AsyncResult<T>(data)
}