package com.android.architecture.example.network

import com.android.architecture.example.models.User
import io.reactivex.Observable


open class MockApiSample : ApiSampleType() {

    override fun fetchUsers(): Observable<List<User>> = Observable.empty()

}