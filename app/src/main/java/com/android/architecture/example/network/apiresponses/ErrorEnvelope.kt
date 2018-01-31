package com.android.architecture.example.network.apiresponses

import com.android.architecture.example.network.exceptions.ApiException


data class ErrorEnvelope(
        var status: String?,
        var error: String?
) {
    companion object {
        fun fromThrowable(t: Throwable): ErrorEnvelope? {
            if (t is ApiException) {
                return t.errorEnvelope()
            }
            return null
        }
    }
}