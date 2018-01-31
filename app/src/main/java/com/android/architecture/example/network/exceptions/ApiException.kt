package com.android.architecture.example.network.exceptions

import com.android.architecture.example.network.apiresponses.ErrorEnvelope
import retrofit2.Response


open class ApiException(
        private val errorEnvelope: ErrorEnvelope,
        response: Response<*>
) : ResponseException(response) {

    fun errorEnvelope(): ErrorEnvelope = errorEnvelope

}