/*
 * Copyright 2017 Kickstarter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ***
 *
 * Original: https://github.com/kickstarter/android-oss/blob/master/app/src/main/java/com/kickstarter/libs/rx/operators/ApiErrorOperator.java
 * Modifications: Some modifiers and annotations have been added by Guillaume Mas.
 */
package com.android.architecture.example.lib.rx.operators

import com.android.architecture.example.network.apiresponses.ErrorEnvelope
import com.android.architecture.example.network.exceptions.ApiException
import com.android.architecture.example.network.exceptions.ResponseException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.ObservableOperator
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response
import java.io.IOException


class ApiErrorOperator<T>(
        private val moshi: Moshi
) : ObservableOperator<T, Response<T>> {

    override fun apply(observer: Observer<in T>): Observer<in Response<T>> {
        return object : Observer<Response<T>> {

            override fun onComplete() {
                observer.onComplete()
            }

            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onNext(response: Response<T>) {
                if (!response.isSuccessful) {
                    try {
                        val type = Types.newParameterizedType(ErrorEnvelope::class.java)
                        response.errorBody()?.let { }
                        val envelope = moshi.adapter<ErrorEnvelope>(type).fromJson(response.errorBody()?.string()
                                ?: "{}")
                        envelope?.let { observer.onError(ApiException(it, response)) }
                                ?: observer.onError(ResponseException(response))
                    } catch (e: IOException) {
                        observer.onError(ResponseException(response))
                    }
                } else {
                    observer.onNext(response.body()!!)
                }
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }
        }
    }

}