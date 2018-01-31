package com.android.architecture.example.network.exceptions

import retrofit2.Response


open class ResponseException(
        private val response: Response<*>
) : RuntimeException() {

    fun response(): Response<*> = response

}