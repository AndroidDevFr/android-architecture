package com.android.architecture.example.lib.rx.operators

import com.android.architecture.example.SampleRobolectricTestCase
import com.android.architecture.example.network.apiresponses.ErrorEnvelope
import com.android.architecture.example.network.exceptions.ApiException
import com.google.gson.Gson
import io.reactivex.subjects.PublishSubject
import io.reactivex.subscribers.TestSubscriber
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Response


class ApiErrorOperatorTest : SampleRobolectricTestCase() {

    @Test
    fun testErrorResponse() {
        val gson = Gson()

        val error: PublishSubject<ErrorEnvelope> = PublishSubject.create()
        val response: PublishSubject<Response<Int>> = PublishSubject.create()
        val result = response.lift(Operators.apiError(gson))

        val resultError = TestSubscriber<ErrorEnvelope>()
        val resultTest = TestSubscriber<Int>()
        result.subscribe(resultTest::onNext)
        error.subscribe(resultError::onNext)

        response.onNext(Response.error<Int>(400, ResponseBody.create(MediaType.parse("application/json"),
                        "{\"status\": \"KO\",\"error\": \"bad request\"}")))

        resultTest.assertNoValues()
        resultError.assertValueCount(1)
    }

}