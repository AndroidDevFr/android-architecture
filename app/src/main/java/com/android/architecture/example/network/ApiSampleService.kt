package com.android.architecture.example.network

import com.android.architecture.example.network.apiresponses.UserEnvelope
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET


interface ApiSampleService {

    @GET("users")
    fun fetchUsers(): Observable<Response<List<UserEnvelope>>>

}