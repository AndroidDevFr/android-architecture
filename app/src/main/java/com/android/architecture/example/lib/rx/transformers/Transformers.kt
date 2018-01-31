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
 * Original: https://github.com/kickstarter/android-oss/blob/master/app/src/main/java/com/kickstarter/libs/rx/transformers/Transformers.java
 * Modifications: Some modifiers and annotations have been added by Guillaume Mas.
 */
package com.android.architecture.example.lib.rx.transformers

import com.android.architecture.example.network.apiresponses.ErrorEnvelope
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject


object Transformers {

    /**
     * Prevents an observable from erroring by chaining `onErrorResumeNext`.
     */
    @JvmStatic
    fun <T> neverError(): NeverErrorTransformer<T> {
        return NeverErrorTransformer()
    }

    /**
     * Emits when an error is thrown in a materialized stream.
     */
    @JvmStatic
    fun <T> errors(): ErrorsTransformer<T> {
        return ErrorsTransformer()
    }

    /**
     * Prevents an observable from erroring on any {@link ApiException} exceptions.
     */
    @JvmStatic
    fun <T> neverApiError(): NeverApiErrorTransformer<T> {
        return NeverApiErrorTransformer()
    }

    /**
     * Prevents an observable from erroring on any {@link ApiException} exceptions,
     * and any errors that do occur will be piped into the supplied
     * errors publish subject. `null` values will never be sent to
     * the publish subject.
     */
    @JvmStatic
    fun <T> pipeApiErrorsTo(errorSubject: PublishSubject<ErrorEnvelope>): NeverApiErrorTransformer<T> {
        return NeverApiErrorTransformer(Consumer { env -> errorSubject.onNext(env) })
    }

    /**
     * Prevents an observable from erroring by chaining `onErrorResumeNext`,
     * and any errors that occur will be piped into the supplied errors publish
     * subject. `null` values will never be sent to the publish subject.
     */
    @JvmStatic
    fun <T> pipeErrorsTo(errorSubject: PublishSubject<Throwable>): NeverErrorTransformer<T> {
        return NeverErrorTransformer(Consumer { env -> errorSubject.onNext(env) })
    }

    /**
     * If called on the main thread, schedule the work immediately. Otherwise delay execution of the work by adding it
     * to a message queue, where it will be executed on the main thread.
     * <p>
     * This is particularly useful for RecyclerViews; if subscriptions in these views are delayed for a frame, then
     * the view temporarily shows recycled content and frame rate stutters. To address that, we can use `observeForUI()`
     * to execute the work immediately rather than wait for a frame.
     */
    @JvmStatic
    fun <T> observeForUI(): ObserveForUITransformer<T> {
        return ObserveForUITransformer()
    }

}