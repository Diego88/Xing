package com.hiberus.mobile.android.xing.common.model

import com.hiberus.mobile.android.model.error.AsyncError
import com.hiberus.mobile.android.model.AsyncResult

sealed class ResourceState<out T> {

    class Loading<T> :
        ResourceState<T>() // Loading can NOT be an object inside a class with a generic type
    data class Success<out T>(val data: T?) : ResourceState<T>()
    data class Error<out T>(val error: AsyncError, val data: T?) : ResourceState<T>()

    companion object {
        private const val UNKNOWN_ERROR_DEBUG_MESSAGE = "Error retrieving data"
        private const val UNKNOWN_ERROR_EXCEPTION_MESSAGE =
            "Cannot retrieving data from AsyncResult response"

        fun <T> AsyncResult<T>.toResourceState(): ResourceState<T> {
            return when (this) {
                is AsyncResult.Success -> Success(this.data)
                is AsyncResult.Error -> Error(this.error, this.data)
                else ->
                    Error(
                        AsyncError.UnknownError(
                            UNKNOWN_ERROR_DEBUG_MESSAGE,
                            Throwable(UNKNOWN_ERROR_EXCEPTION_MESSAGE, null)
                        ), null
                    )
            }
        }
    }
}