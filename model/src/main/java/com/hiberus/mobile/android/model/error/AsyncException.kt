package com.hiberus.mobile.android.model.error

class AsyncException(val asyncError: AsyncError): Exception() {
    override fun toString(): String {
        return asyncError.toString()
    }
}