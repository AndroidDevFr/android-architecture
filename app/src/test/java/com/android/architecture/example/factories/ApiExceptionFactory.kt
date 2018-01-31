package com.android.architecture.example.factories

import com.android.architecture.example.models.User
import com.android.architecture.example.network.apiresponses.ErrorEnvelope
import com.android.architecture.example.network.exceptions.ApiException
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response


object ApiExceptionFactory {

    @JvmStatic
    fun badRequestException(): ApiException {
        val envelope = ErrorEnvelope("KO", "bad request")
        val body = ResponseBody.create(null, "")
        val response = Response.error<Observable<User>>(400, body)

        return ApiException(envelope, response)
    }

}