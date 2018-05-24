package com.android.architecture.example.network

import com.android.architecture.example.lib.rx.operators.ApiErrorOperator
import com.android.architecture.example.lib.rx.operators.Operators
import com.android.architecture.example.models.User
import com.android.architecture.example.network.adapters.UserEnvelopeAdapter
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class ApiSample(
        private val apiSampleService: ApiSampleService,
        private val moshi: Moshi
) : ApiSampleType() {

    override fun fetchUsers(): Observable<List<User>> {
        return apiSampleService.fetchUsers()
                .lift(apiErrorOperator())
                .map(UserEnvelopeAdapter::fromJson)
                .subscribeOn(Schedulers.io())
    }

    private fun <T> apiErrorOperator(): ApiErrorOperator<T> {
        return Operators.apiError(moshi)
    }

}